package com.makersacademy.acebook.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest {

    private User user = new User("testUser1", "randomPassword", Boolean.valueOf("TRUE"));

    @Test
    public void UserHasAllDetails() {
        assertTrue(user.getUsername().contains("testUser1"));
        assertTrue(user.getPassword().contains("randomPassword"));
        assertTrue(user.getEnabled());
    }

}