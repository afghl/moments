package com.moments.services.users;

import com.moments.models.Following;
import com.moments.models.User;
import com.moments.repositories.FollowingRepository;
import com.moments.repositories.UserRepository;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository users;

    @Autowired
    private FollowingRepository followings;

    public Page<User> findByPage(int page, int pageSize) {
        Pageable pageRequest = new PageRequest(page, pageSize);
        return users.findAll(pageRequest);
    }

    public List<User> findFollowers(Long userId) {
        List<Following> followerList = followings.findByUserId(userId);

        List<Long> ids = followerList
                .stream()
                .map(Following::getFollowerId)
                .collect(Collectors.toList());

        return users.findAll(ids);
    }
}
