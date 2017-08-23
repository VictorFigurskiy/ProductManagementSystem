package com.product.system.controllers;

import com.product.system.entity.User;
import com.product.system.entity.UserRole;
import com.product.system.services.UserRoleService;
import com.product.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

/**
 * Created by Sonikb on 22.08.2017.
 */
@Controller
@RequestMapping(value = "/register", method = RequestMethod.POST)
public class RegistrationController {

    private UserService userService;
    private UserRoleService userRoleService;

    @Autowired
    public RegistrationController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public String register(@ModelAttribute("user")User user){
        UserRole userRole = userRoleService.getById(1);
        HashSet<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);
        user.setUserRoles(userRoleSet);
        userService.save(user);
        return "redirect:/login";
    }
}
