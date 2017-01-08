package com.moments.services.followings;

import com.moments.models.User;

public interface FollowingService {
    void followingEachOther(User u1, User u2) throws AlreadyFollowingException;
}
