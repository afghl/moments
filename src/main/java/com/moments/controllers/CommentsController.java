package com.moments.controllers;

import com.moments.models.Comment;
import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.services.comments.CommentService;
import com.moments.services.comments.UserCannotReplyMoment;
import com.moments.services.moments.MomentService;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CommentsController {

    @Autowired
    private MomentService momentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService service;

    @RequestMapping(value = "/api/moments/{momentId}/comments", method = POST, produces = "application/json")
    public String create(
            @PathVariable Long momentId,
            @RequestBody Map<String, String> body,
            Model model
    ) {

        Long userId = Long.valueOf(body.get("userId"));

        Long otherId;
        if (body.get("otherId") != null) {
            otherId = Long.valueOf(body.get("otherId"));
        } else {
            otherId = null;
        }

        System.out.println("user id is " +userId + ",  other id is " + otherId);

        // TODO: render 404 if no moments or user not present.
        Moment m = momentService.findOne(momentId);
        User u = userService.findOne(userId);
        User other = userService.findOne(otherId);


        Integer type = Integer.valueOf(body.get("type"));
        System.out.println("type is " + type);
        String commentBody = body.get("body");

        try {
            Comment c = service.replyOnMoment(type, commentBody, m, u, other);
            model.addAttribute("items", c);
        } catch (UserCannotReplyMoment e) {
            // TODO: capture this error and render fail.
            e.printStackTrace();
        }
        return "jsonTemplate";
    }
}
