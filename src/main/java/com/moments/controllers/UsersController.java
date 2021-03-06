package com.moments.controllers;

import com.moments.models.User;
import com.moments.repositories.UserRepository;
import com.moments.services.users.UserService;
import com.moments.utils.RedisHelper;
import com.moments.utils.RedisKeysPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.moments.utils.RedisKeysPresenter.usersCacheKey;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = GET)
    public String index(
            Model model
    ) {
        model.addAttribute("items", userService.findRandomUsers(5));
        return "jsonTemplate";
    }
}
