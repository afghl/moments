package com.moments.services.followings;

import com.moments.models.Following;
import com.moments.repositories.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingServiceImpl implements FollowingService {

    @Autowired
    private FollowingRepository followings;

    @Override
    public void followingEachOther(Integer userId, Integer followerId) throws AlreadyFollowingException {
        Following f1 = new Following(userId, followerId);
        Following f2 = new Following(followerId, userId);

        // TODO: ensure following uniq.
        followings.save(f1);
        followings.save(f2);
    }
}
