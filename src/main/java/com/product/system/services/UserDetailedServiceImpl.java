package com.product.system.services;

import com.product.system.entity.UserEntity;
import com.product.system.entity.UserRoleEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        UserEntity userEntity = userService.getByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("UserEntity not exist!!!");
        }

        String[] roles = userEntity.getUserRoleEntities()
                .stream()
                .map(UserRoleEntity::getRoleType)
                .toArray(String[]::new);

        return User.withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(roles)
                .build();
//        return new UserDetailsExt(userEntity); // this is for the variant lower
    }


    // Another way to create SpringUser with grantedAuthorities roles(this example is on boolean isAdmin)
/*    private static class UserDetailsExt implements UserDetails {
        private UserEntity user;
        private Collection<SimpleGrantedAuthority> grantedAuthorities;

        public UserDetailsExt(UserEntity user) {
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
