package com.moments.controllers;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.services.feeds.FeedService;
import com.moments.services.users.UserService;
import com.moments.utils.AsyncJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

// return a list of feeds of a user
@Controller
public class FeedsController {

    @Autowired
    private FeedService feedService;

    @Autowired
    private UserService userService;

    @Autowired
    private AsyncJobs jobs;

    @CrossOrigin
    @RequestMapping(value = "/api/users/{userId}/feeds", method = GET)
    public String index(
            @PathVariable Long userId,
            @RequestParam(defaultValue = Long.MAX_VALUE + "", required = false) Long lastMomentId,
            Model model
    ) {
        // TODO: return 404 unless current user
        User currentUser = userService.findOne(userId);

        // TODO: will this cause n + 1 query?
        int limit = 20;
        List<Moment> feed = feedService.findFeedsOfUser(currentUser, limit, lastMomentId);

        if (feed.size() != limit)
            jobs.addUserFeedToRedis(currentUser);

        model.addAttribute("items", feed);
        return "jsonTemplate";
    }

}
