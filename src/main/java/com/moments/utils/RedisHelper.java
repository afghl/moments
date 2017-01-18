package com.moments.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        ids.forEach((id) -> addIdToSortedSet(key, id));
    }

    public List<Long> getSortedSet(String key, int limit, int max) {
        key = NAME_SPACE + key;
        Set<Object> result = template.opsForZSet().reverseRangeByScore(key, -1, max - 1, 0, limit);

        return convertSetToArray(result, limit);
    }

    private List<Long> convertSetToArray(Set set, int limit) {
        List<Long> r = new ArrayList<>(limit);

        set.forEach((v) -> r.add(Long.parseLong((String) v)));
        return r;
    }

}
