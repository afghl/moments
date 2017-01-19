package com.moments.utils;

import com.moments.models.User;
import com.moments.services.moments.MomentService;
import com.moments.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.moments.utils.RedisKeysPresenter.userFeedCacheKey;
import static com.moments.utils.RedisKeysPresenter.usersCacheKey;

@Component
public class AsyncJobs {

    @Autowired
    private UserService userService;

    @Autowired
    private MomentService momentService;

    @Autowired
    private RedisHelper redisHelper;

    @Async
    public void addUserFeedToRedis(User u) {
        List<User> followers = userService.findFollowers(u.getId(), true);
        List<Long> ids = momentService.findMomentIdsOfUsers(followers);
        Set<Long> idSet = new HashSet<>(ids);

        redisHelper.addIdsToSortedSet(userFeedCacheKey(u), idSet);
    }

    @Async
    public void addUserIdsToRedis() {
        int pageSize = 1000;
        int page = 0;
        Page<User> users = null;
        String key = usersCacheKey();

        do {
            users = userService.findByPage(page++, pageSize);
            users.forEach((u) -> redisHelper.addIdToSet(key, u.getId()));
        } while (users.getSize() < pageSize);
    }

    @Async
    public void addFollowerMomentsToUserFeedList(User user, User follower) {
        List<Long> ids = momentService.findMomentIdsOfUsers(Arrays.asList(follower));
        Set<Long> idSet = new HashSet<>(ids);
        System.out.println("---addFollowerMomentsToUserFeedList---");
        System.out.println(idSet);
        redisHelper.addIdsToSortedSet(userFeedCacheKey(user), idSet);
    }

    @Async
    public void removeFollowerMomentsFromUserFeedList(User user, User follower) {
        List<Long> ids = momentService.findMomentIdsOfUsers(Arrays.asList(follower));
        Set<Long> idSet = new HashSet<>(ids);
        System.out.println("---removeFollowerMomentsFromUserFeedList---");
        System.out.println(idSet);
        redisHelper.removeIdsFromSortedSet(userFeedCacheKey(user), idSet);
    }
}
