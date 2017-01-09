package com.moments.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moments.MomentsApplication;
import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.MomentRepository;
import com.moments.repositories.UserRepository;
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
public class MomentsControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MomentRepository momentRepository;

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws JsonProcessingException {
        User u1 = createUser();

        momentRepository.save(new Moment("moment1", u1.getId()));
        momentRepository.save(new Moment("moment2", u1.getId()));
        momentRepository.save(new Moment("moment3", u1.getId()));

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private User createUser() {
        userRepository.save(new User("张三", null));
        return userRepository.findAll().get(0);
    }

    @Test
    public void testIndex() throws Exception {
        User u = userRepository.findAll().get(0);
        MvcResult result = mvc.perform(get("/users/" + u.getId() + "/moments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }
}
