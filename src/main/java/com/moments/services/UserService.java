package com.moments.services;

import com.moments.models.User;
import org.springframework.data.domain.Page;

public interface UserService {
    Iterable<User> findAll();

    Page<User> findByPage(int page, int pageSize);
}
