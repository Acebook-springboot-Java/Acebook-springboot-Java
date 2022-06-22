package com.makersacademy.acebook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.makersacademy.acebook.SecurityConfiguration;
import org.springframework.stereotype.Service;

import com.makersacademy.acebook.repository.UserRepository;

import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Authority;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AuthoritiesRepository authoritiesRepository;
  @Autowired
  private SecurityConfiguration securityConfiguration;

  public User createUser(User user){
      System.out.println(user);
      PasswordEncoder encoder = securityConfiguration.passwordEncoder();
      //user.setUsername(user.getUsername());
      String password = user.getPassword();
      
      System.out.println("***userpass****");
      System.out.println(password);
      System.out.println("***userpass****");
    
      String encodedPassword = encoder.encode(password);
      
      System.out.println("***encodedPass****");
      System.out.println(encodedPassword);
      System.out.println("***encodedPass****");
    
      user.setPassword(encodedPassword);
      Authority authority = new Authority(user.getUsername(), "ROLE_USER");
      userRepository.save(user);
      authoritiesRepository.save(authority);
      return user;
  }
}
