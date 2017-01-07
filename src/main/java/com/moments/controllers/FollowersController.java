package com.moments.controllers;

import com.moments.models.User;
import com.moments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/followers")
public class FollowersController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "", method = GET)
    public String index(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "userId") int userId,
            Model model
    ) {
        List<User> followers = service.findFollowers(userId, page, pageSize);
        model.addAttribute("items", followers);
        return "jsonTemplate";
    }
}
