package com.moments.services.users;

import com.moments.models.Following;
import com.moments.models.User;
import com.moments.repositories.FollowingRepository;
import com.moments.repositories.UserRepository;
import com.moments.utils.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.moments.utils.RedisKeysPresenter.usersCacheKey;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository users;

    @Autowired
    private FollowingRepository followings;

    @Autowired
    private RedisHelper helper;

    public Page<User> findByPage(int page, int pageSize) {
        Pageable pageRequest = new PageRequest(page, pageSize);
        return users.findAll(pageRequest);
    }

    public User findOne(Long id) {
        if (id == null)
            return null;
        else
            return users.findOne(id);
    }

    @Override
    public List<User> findRandomUsers(int limit) {
        // TODO: filter out followers of current user.
        List<Long> ids = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            ids.add(helper.getRandomIdInSet(usersCacheKey()));
        }
        return users.findAll(ids);
    }

    public List<User> findFollowers(Long userId, boolean includeSelf) {
        List<Following> followerList = followings.findByUserId(userId);

        List<Long> ids = followerList
                .stream()
                .map(Following::getFollowerId)
                .collect(Collectors.toList());

        List<User> result = users.findAll(ids);

        if (includeSelf)
            result = addUserToList(result, userId);

        return result;
    }

    // FIXME: when jpa return empty result, cannot call add api.
    private List<User> addUserToList(List<User> result, Long userId) {
        User u = users.findOne(userId);
        
        if (result.isEmpty()) {
            User[] us = {u};
            result = Arrays.asList(us);
        } else {
            result.add(u);
        }

        return result;
    }
}
