package com.makersacademy.acebook.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
// @DataJpaTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @WithMockUser("spring")
    @Test
    public void shouldReceiveEncodedPassword() {
        User user = new User("testUser", "testPassword");
        User encryptedUser = userService.createUser(user);
        String expected = encryptedUser.getPassword();
        User userFromDB = userRepository.findByUsername(user.getUsername());
        assertThat(userFromDB.getPassword()).isEqualTo(expected);
    }
}
