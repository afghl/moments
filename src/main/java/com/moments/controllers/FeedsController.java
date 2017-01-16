package com.moments.controllers;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.UserRepository;
import com.moments.services.feeds.FeedService;
import com.moments.services.moments.MomentService;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

// return a list of feeds of a user
@Controller
public class FeedsController {

    @Autowired
    private MomentService service;

    @Autowired
    private FeedService feedService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/api/users/{userId}/feeds", method = GET)
    public String index(
            @PathVariable Long userId,
            @RequestParam(defaultValue = Long.MAX_VALUE + "", required = false) Long lastMomentId,
            Model model
    ) {
        // TODO: when the cached feed is not existed, it return empty array.
        //       ensure the cached feed is valid.
        //       USE async
        // TODO: refactor
        User currentUser = new User();
        currentUser.setId(userId);

        List<Moment> feed = feedService.findFeedsOfUser(currentUser, 20, lastMomentId);

        model.addAttribute("items", feed);

        return "jsonTemplate";
    }
}
