package com.moments.services.followings;

import com.moments.models.Following;
import com.moments.models.User;
import com.moments.repositories.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingServiceImpl implements FollowingService {

    @Autowired
    private FollowingRepository followings;

    public void followingEachOther(User u1, User u2) throws AlreadyFollowingException {
        Following f1 = new Following(u1, u2);
        Following f2 = new Following(u2, u1);

        // TODO: ensure following uniq.
        followings.save(f1);
        followings.save(f2);
    }
}
