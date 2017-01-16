package com.moments.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisHelper {

    // TODO: differ in environment.
    public static final String NAME_SPACE = "moment-dev:";

    @Autowired
    private RedisTemplate<String, Object> template;


    public void addIdToSortedSet(String key, Long id) {
        key = NAME_SPACE + key;
        template.opsForZSet().add(key, id + "", id);
    }

    public void addIdsToSortedSet(String key, Set<Long> ids) {
        // TODO: improve performance.
        String finalKey = NAME_SPACE + key;
        ids.forEach((id) -> addIdToSortedSet(finalKey, id));
    }
//
//    public Set<Long> getFromSortedSet(String key) {
////        template.opsForZSet().
//        return null;
//    }
}
