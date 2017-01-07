package com.moments.services;

import com.moments.models.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<User> findByPage(int page, int pageSize);

    List<User> findFollowers(int userId, int page, int pageSize);
}
