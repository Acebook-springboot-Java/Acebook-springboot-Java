package com.makersacademy.acebook.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.makersacademy.acebook.SecurityConfiguration;
import com.makersacademy.acebook.entity.UserPrinciple;

import org.springframework.stereotype.Service;

import com.makersacademy.acebook.repository.UserRepository;

import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Authority;

@Service
public class UserService implements UserDetailsService {
  private static final Logger logger = LogManager.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AuthoritiesRepository authoritiesRepository;
  @Autowired
  private SecurityConfiguration securityConfiguration;

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

  public User createUser(User user) {
    PasswordEncoder encoder = securityConfiguration.passwordEncoder();
    // user.setUsername(user.getUsername());
    String password = user.getPassword();
    String encodedPassword = encoder.encode(password);
    logger.info("***userpass****" + password);
    logger.info("***encodedPass****" + encodedPassword);
    user.setPassword(encodedPassword);
    Authority authority = new Authority(user.getUsername(), "ROLE_USER");
    userRepository.save(user);
    authoritiesRepository.save(authority);
    return user;
  }

}
