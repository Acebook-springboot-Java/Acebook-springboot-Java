package com.makersacademy.acebook.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.makersacademy.acebook.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
// @DataJpaTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void shouldReceiveEncodedPassword() {
        User user = new User("testUser", "testPassword");
        System.out.println(userService.createUser(user));
        userService.createUser(user);

    }
}
