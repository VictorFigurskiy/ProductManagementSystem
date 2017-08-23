package com.product.system.controllers;

import com.product.system.entity.Product;
import com.product.system.entity.User;
import com.product.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Sonikb on 17.08.2017.
 */
@Controller
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // localhost:8080/user/list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView productsList(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.getAll();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(Integer.parseInt(id));
        userService.remove(user);
        List<User> userList = userService.getAll();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("users");
        return modelAndView;
    }
}
