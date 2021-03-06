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

    // TODO: batch
    public void addIdToSet(String key, Long id) {
        key = NAME_SPACE + key;
        template.opsForSet().add(key, id.toString());
    }

    public Long getRandomIdInSet(String key) {
        key = NAME_SPACE + key;
        Object r =  template.opsForSet().randomMember(key);
        return Long.parseLong((String) r);
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

    // TODO: batch
    public void removeIdsFromSortedSet(String key, Set<Long> idSet) {
        idSet.forEach((v) -> template.opsForZSet().remove(NAME_SPACE + key, v.toString()));
    }

    private List<Long> convertSetToArray(Set set, int limit) {
        List<Long> r = new ArrayList<>(limit);

        set.forEach((v) -> r.add(Long.parseLong((String) v)));
        return r;
    }

}
