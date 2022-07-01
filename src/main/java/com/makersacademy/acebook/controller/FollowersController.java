package com.makersacademy.acebook.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.makersacademy.acebook.model.Follower;
import com.makersacademy.acebook.repository.FollowerRepository;
import com.makersacademy.acebook.services.ResponseHandler;

@Controller
public class FollowersController {
    private static final Logger logger = LogManager.getLogger(FollowersController.class);

    @Autowired
    FollowerRepository followerRepository;

    @GetMapping("/Followers")
    public ResponseEntity<?> index() {
        logger.info("---------GET request send to /Followers---------");
        Iterable<Follower> followers = followerRepository.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "all Followers received", followers);
    }

    @PostMapping("/Followers")
    public ResponseEntity<?> create(@RequestBody Follower follower, Long userId, Long followerId) {
        logger.info("---------Follower request send to /Followers---------");
        followerRepository.save(follower);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Followers created", follower);
    }
/*
    @GetMapping("/Followers/{id}")
    public ResponseEntity<?> getFollower(@PathVariable("id") Long id) {
        logger.info("---------GET request send to /Followers/{id}------");
        try {
          Follower follower = followerRepository.findById(id);
          return ResponseHandler.generateResponse(HttpStatus.OK, true, "Follower found", follower);
        } 
        catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Follower not found", id);
          }
    }
 */
    @DeleteMapping("/Followers/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        logger.info("---------DELETE request send to /Followers---------");
        try {
        followerRepository.deleteById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Follower deleted", id);
        }
        catch (Exception e) {
          return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Follower not found", id);  
        }
    }
}