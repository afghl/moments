package com.moments.controllers;

import com.moments.models.User;
import com.moments.services.followings.AlreadyFollowingException;
import com.moments.services.followings.FollowingService;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class FollowersController {

    @Autowired
    private UserService service;

    @Autowired
    private FollowingService followingService;

    @RequestMapping(value = "/api/users/{userId}/followers", method = GET)
    public String index(
            @PathVariable Long userId,
            Model model
    ) {
        List<User> followers = service.findFollowers(userId, true);
        model.addAttribute("items", followers);
        return "jsonTemplate";
    }

    @RequestMapping(value = "/api/users/{userId}/followers", method = POST)
    public String create(@PathVariable Long userId, @RequestParam Long followerId, Model model) {
        try {
            followingService.followingEachOther(userId, followerId);
            // use standard http code for status
            model.addAttribute("status", "success");
        } catch (AlreadyFollowingException e) {
            e.printStackTrace();
            model.addAttribute("status", "failed");
        }

        return "jsonTemplate";
    }
}
