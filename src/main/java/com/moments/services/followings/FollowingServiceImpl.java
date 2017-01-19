package com.moments.services.followings;

import com.moments.models.Following;
import com.moments.repositories.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FollowingServiceImpl implements FollowingService {

    @Autowired
    private FollowingRepository followings;

    @Override
    public void follow(Long userId, Long followerId) throws AlreadyFollowingException {
        // TODO: ensure following uniq.
        followings.save(createFollowingsFrom(userId, followerId));

    }


    @Override
    public void unfollow(Long userId, Long followerId) {
        followings.deleteByUserIdAndFollowerId(userId, followerId);
        followings.deleteByUserIdAndFollowerId(followerId, userId);
    }

    private List<Following> createFollowingsFrom(Long userId, Long followerId) {
        Following f1 = new Following(userId, followerId);
        Following f2 = new Following(followerId, userId);

        return new ArrayList<>(Arrays.asList(f1, f2));
    }

}
