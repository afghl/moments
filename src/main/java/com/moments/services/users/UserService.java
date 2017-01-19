package com.moments.services.users;

import com.moments.models.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<User> findByPage(int page, int pageSize);

    User findOne(Long userId);

    List<User> findFollowers(Long userId, boolean includeSelf);

    List<User> findRandomUsers(int limit);
}
