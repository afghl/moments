package com.moments.utils;

import com.moments.models.User;

// handler all redis keys for use
public class RedisKeysPresenter {

    public static final String USERS_CACHE_KEY = "all-users";

    public static String userFeedCacheKey(User u) {
        return "user:" + u.getId() + ":feeds";
    }

    // all users cache
    public static String usersCacheKey() {
        return USERS_CACHE_KEY;
    }
}
