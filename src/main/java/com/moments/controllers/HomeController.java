package com.moments.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        return "test";
    }
}
