package com.moments.utils;

import com.moments.models.User;

// handler all redis keys for use
public class RedisKeysPresenter {

    public static String userFeedCacheKey(User u) {
        return "user:" + u.getId() + ":feeds";
    }

    // all users cache
    public static String usersCacheKey() {
        return "all-users";
    }
}
