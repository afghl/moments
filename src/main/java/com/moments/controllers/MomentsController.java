package com.moments.controllers;


import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.services.moments.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MomentsController {

    @Autowired
    private MomentService service;

    @CrossOrigin
    @RequestMapping(value = "/users/{userId}/moments", method = GET)
    public String index(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0", required = false) Long lastMomentId,
            Model model
    ) {
        User u = new User();
        u.setId(userId);

        List<Moment> moments = service.findMomentsOfUser(u, lastMomentId);

        model.addAttribute("items", moments);
        return "jsonTemplate";
    }
}
