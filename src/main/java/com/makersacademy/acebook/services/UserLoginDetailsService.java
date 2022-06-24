package com.makersacademy.acebook.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.makersacademy.acebook.entity.UserPrinciple;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

@Service
public class UserLoginDetailsService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserLoginDetailsService.class);

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            logger.error("User not found in the database.");
            throw new UsernameNotFoundException("User not found in the database.");
        } else {
            logger.info("User {} found in the database", user.getUsername());
        }

        return new UserPrinciple(user);
    }
}
