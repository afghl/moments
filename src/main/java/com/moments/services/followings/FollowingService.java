package com.moments.services.followings;

public interface FollowingService {
    void follow(Long userId, Long followerId) throws AlreadyFollowingException;

    void unfollow(Long userId, Long followerId);
}
