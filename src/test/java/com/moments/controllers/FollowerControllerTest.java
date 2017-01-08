package com.moments.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moments.MomentsApplication;
import com.moments.models.User;
import com.moments.repositories.FollowingRepository;
import com.moments.repositories.UserRepository;
import com.moments.services.followings.AlreadyFollowingException;
import com.moments.services.followings.FollowingService;
import com.moments.services.followings.FollowingServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MomentsApplication.class)
@WebAppConfiguration
@Transactional
public class FollowerControllerTest {
    @Autowired
    FollowingService followings;

    @Autowired
    UserRepository userRepository;

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws AlreadyFollowingException {
        User u1 = new User("张三", null);
        User u2 = new User("李四", null);


        userRepository.save(u1);
        userRepository.save(u2);

        followings.followingEachOther(u1, u2);

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testIndex() throws Exception {
        MvcResult result = mvc.perform(get("/followers?userId=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }
}
