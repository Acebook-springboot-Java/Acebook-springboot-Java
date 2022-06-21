package com.makersacademy.acebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    // @GetMapping("/users/new")
    // public String signup(ModelMap model) {
    // model.addAttribute("user", new User());
    // // return new ModelAndView("redirect:users/new", model);
    // // // return new RedirectView("users/new");
    // return "users/new";
    // }

    @PostMapping("/users")
    public ResponseEntity<?> signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
