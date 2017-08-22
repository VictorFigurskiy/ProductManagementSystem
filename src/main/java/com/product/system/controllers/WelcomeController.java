package com.product.system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * Created by Sonikb on 17.08.2017.
 */
@Controller
@RequestMapping(value = "/")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "index";
    }



    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String logout() {
        // http://localhost:8080/login?logout
        return "redirect:/login?logout";
    }
}
