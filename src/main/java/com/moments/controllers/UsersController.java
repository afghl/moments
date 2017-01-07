package com.moments.controllers;

import com.moments.models.User;
import com.moments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "", method = GET)
    public Page<User> index(
        @RequestParam(value="page", defaultValue = "0", required = false) int page,
        @RequestParam(value="pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return service.findByPage(page, pageSize);
    }
}
