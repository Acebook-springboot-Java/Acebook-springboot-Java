package com.makersacademy.acebook.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;

@Service
public class UserService {
  private static final Logger logger = LogManager.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AuthoritiesRepository authoritiesRepository;
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  public User createUser(User user) {
    // BCryptPasswordEncoder encoder = securityConfiguration.passwordEncoder();
    // // user.setUsername(user.getUsername());
    String password = user.getPassword();
    String encodedPassword = bCryptPasswordEncoder.encode(password);
    logger.info("***username****" + password);
    logger.info("***encodedPass****" + encodedPassword);
    user.setPassword(encodedPassword);
    Authority authority = new Authority(user.getUsername(), "ROLE_USER");
    userRepository.save(user);
    authoritiesRepository.save(authority);
    return user;
  }

}
