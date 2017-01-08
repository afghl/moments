package com.moments.controllers;

import com.moments.models.User;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/followers")
public class FollowersController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "", method = GET)
    public String index(
            @RequestParam(value = "userId") int userId,
            Model model
    ) {
        List<User> followers = service.findFollowers(userId);
        model.addAttribute("items", followers);
        return "jsonTemplate";
    }
}
