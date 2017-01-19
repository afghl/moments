package com.moments.controllers;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.services.followings.AlreadyFollowingException;
import com.moments.services.followings.FollowingService;
import com.moments.services.users.UserService;
import com.moments.utils.AsyncJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class FollowersController {

    @Autowired
    private UserService service;

    @Autowired
    private FollowingService followingService;

    @Autowired
    private AsyncJobs jobs;

    @RequestMapping(value = "/api/users/{userId}/followers", method = GET)
    public String index(
            @PathVariable Long userId,
            Model model
    ) {
        List<User> followers = service.findFollowers(userId, true);
        model.addAttribute("items", followers);
        return "jsonTemplate";
    }

    // use "type" param to handle both follow and unfollow operation.
    // to reduce client complexity.
    @RequestMapping(value = "/api/users/{userId}/followers", method = POST, produces = "application/json")
    public String create(
            @PathVariable Long userId,
            @RequestBody Map<String, String> body,
            Model model
    ) {
        Long followerId = Long.parseLong(body.get("followerId"));
        String type = body.get("type");

        User user = service.findOne(userId);
        User follower = service.findOne(followerId);
        // TODO: render 404 when user or follower is not found.
        if ("follow".equals(type)) {
            handleFollow(user, follower, model);
        } else if ("unfollow".equals(type)) {
            handleUnfollow(user, follower, model);
        }

        return "jsonTemplate";
    }

    private void handleFollow(User u, User f, Model model) {
        try {
            followingService.follow(u.getId(), f.getId());
            // TODO: use standard http code for status
            // add follower's moment to user's feed.
            jobs.addFollowerMomentsToUserFeedList(u, f);
            // add user's moments to follower's feed.
            jobs.addFollowerMomentsToUserFeedList(f, u);
        } catch (AlreadyFollowingException e) {
            e.printStackTrace();
        }
    }

    private void handleUnfollow(User u, User f, Model model) {
        followingService.unfollow(u.getId(), f.getId());
        // remove follower's moment to user's feed.
        jobs.removeFollowerMomentsFromUserFeedList(u, f);
        // remove user's moments to follower's feed.
        jobs.removeFollowerMomentsFromUserFeedList(f, u);
    }
}
