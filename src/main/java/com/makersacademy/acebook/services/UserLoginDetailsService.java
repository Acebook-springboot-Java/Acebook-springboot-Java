package com.makersacademy.acebook.services;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.makersacademy.acebook.repository.UserRepository;

@Service
public class UserLoginDetailsService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserLoginDetailsService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("---------FINDING USERNAME---------");
        com.makersacademy.acebook.model.User user = userRepository.findByUsername(username);

        if (user == null) {
            logger.error("---------User NOT FOUND IN DB.---------");
            throw new UsernameNotFoundException("User not found in the database.");
        }
        logger.info("---------USER {} FOUND IN DB ---------", user.getUsername());
        org.springframework.security.core.userdetails.User securityUser = new User(user.getUsername(),
                user.getPassword(), new ArrayList<>());
        return securityUser;
    }
}
