package com.makersacademy.acebook.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.services.ResponseHandler;

// @RunWith(SpringRunner.class)
// @ExtendWith(SpringExtension.class)
public class UsersControllerTest {

    // @Autowired
    // UserRepository userRepository;
    // @Autowired
    // AuthoritiesRepository authoritiesRepository;
    // @Autowired
    // ResponseHandler responseHandler;

    // @Autowired
    // private MockMvc mockMvc;

    // @Test
    // public void createMockMvc() {
    // assertNotNull(mockMvc);
    // }

    // @Test
    // public void createUserAPI() throws Exception {
    // this.mockMvc.perform(post("/users"))
    // .content(asJsonString(new User("testuser1", "password")))
    // .contentType(MediaType.APPLICATION_JSON)
    // .accept(MediaType.APPLICATION_JSON)
    // .andExpect(MockMvcResultMatchers.status().isCreated());
    // // .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").exists());
    // }

    // public static String asJsonString(final Object obj) {
    // try {
    // return new ObjectMapper().writeValueAsString(obj);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

}
