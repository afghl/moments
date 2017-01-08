package com.moments.controllers;

import com.moments.models.User;
import com.moments.services.followings.AlreadyFollowingException;
import com.moments.services.followings.FollowingService;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/followers")
public class FollowersController {

    @Autowired
    private UserService service;

    @Autowired
    private FollowingService followingService;

    @RequestMapping(method = GET)
    public String index(
            @RequestParam int userId,
            Model model
    ) {
        List<User> followers = service.findFollowers(userId);
        model.addAttribute("items", followers);
        return "jsonTemplate";
    }

    @RequestMapping(method = POST)
    public String create(@RequestParam int userId, @RequestParam int followerId, Model model) {
        try {
            followingService.followingEachOther(userId, followerId);
            model.addAttribute("status", "success");
        } catch (AlreadyFollowingException e) {
            e.printStackTrace();
            model.addAttribute("status", "failed");
        }

        return "jsonTemplate";
    }
}
