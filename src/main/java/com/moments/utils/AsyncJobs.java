package com.moments.utils;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.services.moments.MomentService;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AsyncJobs {

    @Autowired
    private UserService userService;

    @Autowired
    private MomentService momentService;

    @Autowired
    private RedisHelper redisHelper;

    @Async
    public void syncUserFeedToRedis(User u) {
        List<User> followers = userService.findFollowers(u.getId(), true);
        List<Long> ids = momentService.findMomentIdsOfUsers(followers);
        Set<Long> idSet = new HashSet<>(ids);
        redisHelper.addIdsToSortedSet(u.getRedisFeedKey(), idSet);
    }
}
