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
        String key = "users:53:feeds";
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
        String key = "users:43:feeds";
        SortedSet<Long> ids = new TreeSet<>();
        ids.add(4L);
        ids.add(6L);
        ids.add(8L);
        ids.add(1L);

        helper.addIdsToSortedSet(key, ids);
        List<Long> result = helper.getSortedSet(key, 0, 10);

        assertEquals(ids.size(), result.size());
        int i = 3;
        for (Long id : ids) {
            assertEquals(id, result.get(i--));
        }
    }

    @Test
    public void getOperation() {
        String key = "users:10:feeds";
        long[] ids = { 3, 6, 8, 5, 1, 2, 7 };
        for (long id : ids)
            helper.addIdToSortedSet(key, id);

        // get 3 ids that less then 5.
        List<Long> result = helper.getSortedSet(key, 3, 5);
        System.out.println(result);

        assertEquals(result.size(), 3);
        assertEquals(3l, (long) result.get(0));
        assertEquals(2l, (long) result.get(1));
        assertEquals(1l, (long) result.get(2));
    }
}