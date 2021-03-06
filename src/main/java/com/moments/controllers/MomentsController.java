package com.moments.controllers;


import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.UserRepository;
import com.moments.services.moments.MomentService;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MomentsController {

    @Autowired
    private MomentService service;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/api/users/{userId}/moments", method = GET)
    public String index(
            @PathVariable Long userId,
            @RequestParam(defaultValue = Long.MAX_VALUE + "", required = false) Long lastMomentId,
            Model model
    ) {
        User u = new User();
        u.setId(userId);

        List<Moment> moments = service.findMomentsOfUser(u, lastMomentId);

        model.addAttribute("items", moments);
        return "jsonTemplate";
    }

    @RequestMapping(value = "/api/users/{userId}/moments", method = POST, produces = "application/json")
    public String create(
            @PathVariable Long userId,
            @RequestBody Map<String, String> body,
            Model model
    ) {
        // TODO: throw error when user not existed.
        User u = userRepository.findOne(userId);

        Moment m = new Moment();
        m.setBody(body.get("body"));

        // TODO: decouple service and redis task.
        //       refactor pub action to async jobs?.
        service.saveAndPubToFollowers(m, u);

        model.addAttribute("items", service.save(m));
        return "jsonTemplate";
    }
}
