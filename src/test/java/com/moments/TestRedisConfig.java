package com.moments;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = SpringRedisConfig.class )
public class TestRedisConfig {

    @Autowired
    private RedisTemplate<String, String> template;

    @Test
    public void testSetAndGet() {
        template.opsForValue().set("mykey", "myvalue");
        assertEquals(template.opsForValue().get("mykey"), "myvalue");
    }

}
