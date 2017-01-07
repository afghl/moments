package com.moments.controllers;


import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.services.MomentService;
import com.moments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/moments")
public class MomentsController {

    @Autowired
    private MomentService service;

    @RequestMapping(value = "", method = GET)
    public String index(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0", required = false) int lastMomentId,
            Model model
    ) {
        User u = new User();
        u.setId(userId);

        List<Moment> moments = service.findMomentsOfUser(u, lastMomentId);

        model.addAttribute("items", moments);
        return "jsonTemplate";
    }
}
