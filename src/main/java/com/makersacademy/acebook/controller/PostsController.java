package com.makersacademy.acebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.services.ResponseHandler;

@Controller
public class PostsController {
    private static final Logger logger = LogManager.getLogger(PostsController.class);

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<?> index() {
        logger.info("---------GET request send to /posts---------");
        Iterable<Post> posts = postRepository.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "all posts received", posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<?> create(@RequestBody Post post) {
        logger.info("---------POST request send to /posts---------");
        postRepository.save(post);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "posts created", post);
    }
}
