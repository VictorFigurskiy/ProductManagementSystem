package com.product.system.services;

import com.product.system.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sonikb on 16.08.2017.
 */
public class UserDetailedServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailedServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.product.system.entity.User user = userService.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not exist!!!");
        }

        String[] roles = user.getUserRoles()
                .stream()
                .map(UserRole::getRoleType)
                .toArray(String[]::new);

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
//        return new UserDetailsExt(user); // this is for the variant lower
    }


    // Another way to create SpringUser with grantedAuthorities roles(this example is on boolean isAdmin)
/*    private static class UserDetailsExt implements UserDetails {
        private User user;
        private Collection<SimpleGrantedAuthority> grantedAuthorities;

        public UserDetailsExt(User user) {
            this.user = user;
            this.grantedAuthorities = new ArrayList<>();
            if (user.isAdmin()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return grantedAuthorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }*/
}
