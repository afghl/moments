package com.moments.services.followings;

public interface FollowingService {
    void followingEachOther(Long userId, Long followerId) throws AlreadyFollowingException;
}
