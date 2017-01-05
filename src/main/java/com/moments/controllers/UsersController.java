package com.moments.controllers;

import com.moments.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> index() {
        List<User> users = new ArrayList<>();
        User u = new User();
        u.setId(1);
        u.setName("cool");
        users.add(u);

        return users;
    }
}
