package com.makersacademy.acebook.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.Post;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {
    @Autowired
    UsersController usersController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(usersController).isNotNull();
    }

    // @Autowired
    // private MockMvc mockMvc;

    // @Test
    // public void createUserAPI() throws Exception {
    // mockMvc.perform(MockMvcRequestBuilders
    // .post("/employees")
    // .content(asJsonString(new Post("random contents")))
    // .contentType(MediaType.APPLICATION_JSON)
    // .accept(MediaType.APPLICATION_JSON))
    // .andExpect(status().isCreated())
    // .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    // }

    // public static String asJsonString(final Object obj) {
    // try {
    // return new ObjectMapper().writeValueAsString(obj);
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

}
