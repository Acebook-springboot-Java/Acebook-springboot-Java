package com.makersacademy.acebook.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.services.ResponseHandler;
import com.makersacademy.acebook.services.UserService;

@Controller
public class UsersController {
    private static final Logger logger = LogManager.getLogger(UsersController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> signup(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            userService.createUser(user);
            logger.info("----------User: " + user.getUsername() + " created.");
            return ResponseHandler.generateResponse(HttpStatus.CREATED, false, "username created", user);
        }
        return ResponseHandler.generateResponse(HttpStatus.CONFLICT, true, "duplicated username", user);

    }
}