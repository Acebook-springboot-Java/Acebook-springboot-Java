package com.makersacademy.acebook.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.services.ResponseHandler;

@Controller
public class UsersController {
    private static final Logger logger = LogManager.getLogger(UsersController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @PostMapping("/users")
    public ResponseEntity<?> signup(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            userRepository.save(user);
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authoritiesRepository.save(authority);
            logger.info("----------User: " + user.getUsername() + " created.");
            logger.info("----------Authority given to " + authority.getUsername() + ": " + authority.getAuthority());
            return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "username created", user);
        }
        return ResponseHandler.generateResponse(HttpStatus.CONFLICT, false, "duplicated username", user);

    }
}
