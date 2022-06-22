package com.makersacademy.acebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.services.ResponseHandler;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<?> index() {
        Iterable<Post> posts = postRepository.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "all posts received", posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<?> create(@RequestBody Post post) {
        postRepository.save(post);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "posts created", post);
    }
}
