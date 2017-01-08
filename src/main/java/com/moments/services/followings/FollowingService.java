package com.moments.services.followings;

public interface FollowingService {
    void followingEachOther(Integer userId, Integer followerId) throws AlreadyFollowingException;
}
