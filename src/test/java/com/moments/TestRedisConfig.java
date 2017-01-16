package com.moments;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringRedisConfig.class)
public class TestRedisConfig {

    @Autowired
    private RedisTemplate<String, String> template;

    @Test
    public void testSetAndGet() {
        // TODO: ensure redis test can be rollback.
        template.opsForValue().set("mykeytest", "myvaluetest");
        assertEquals(template.opsForValue().get("mykeytest"), "myvaluetest");
    }

}
