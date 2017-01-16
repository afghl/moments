package com.moments;


import com.moments.utils.RedisHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MomentsApplication.class)
public class RedisHelperTest {

    @Autowired
    private RedisHelper helper;

    @Autowired
    private RedisTemplate<String, Object> template;

    @Test
    public void testAddToSortedSet() {
        String key = "users:4:feeds";
        Long id = 3L;

        helper.addIdToSortedSet(key, id);
        long count = template.opsForZSet().count(RedisHelper.NAME_SPACE + key, 0, 10);

        Set<Object> ids = template.opsForZSet().range(RedisHelper.NAME_SPACE + key, 0, 10);

        assertEquals(ids.size(), 1);
        String idFromRedis = (String)(ids.toArray()[0]);
        System.out.println(idFromRedis);

        assertEquals(idFromRedis, id.toString());
    }

    @Test
    public void testBatchAddToSortedSet() {
        String key = "users:20:feeds";
        SortedSet<Long> ids = new TreeSet<>();
        ids.add(4L);
        ids.add(6L);
        ids.add(8L);
        ids.add(1L);

        // TODO: refactor this
        helper.addIdsToSortedSet(key, ids);
        List<Long> result = helper.getSortedSet(key, 2, 10);

        int i = 0;
        for (Long id : ids) {
            assertEquals(id, result.get(i++));
        }
    }
}