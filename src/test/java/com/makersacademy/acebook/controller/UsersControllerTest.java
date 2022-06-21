package com.makersacademy.acebook.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {
    @Autowired
    UsersController usersController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(usersController).isNotNull();
    }
    // @Test.acebook.controller.UsersControllerTest.contextLoads(UsersControllerTest.java:19)

    // public void createUserAPI() throws Exception {
    // mockMvc.perform(MockMvcRequestBuilders.post("/users")
    // .content(asJsonString(new User(null, "firstName4", "lastName4",
    // "email4@mail.com")))
    // .contentType(MediaType.APPLICATION_JSON)
    // .accept(MediaType.APPLICATION_JSON))
    // .andExpect(status().isCreated())
    // );
    // }
}
