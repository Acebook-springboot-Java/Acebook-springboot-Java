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
import org.springframework.data.rest.webmvc.ResourceNotFoundException;


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

    @PatchMapping("/posts/{id}")
    public ResponseEntity<?> update(@RequestBody Post post, @PathVariable("id") Long id) {
        logger.info("---------PATCH request sent to /posts{id}------");
        try {
          String content = post.toString();
          Post existingPost = postRepository.findById(id).get();
          Post updatedPost = existingPost.setContent(content);
          return ResponseHandler.generateResponse(HttpStatus.OK, true, "post edited", updatedPost);}
        catch (Exception e) {
          return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "post not found", post)
        }
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        logger.info("---------POST request send to /posts---------");
        postRepository.deleteById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "post deleted", post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        logger.info("---------GET request send to /posts{id}------");
    }
}


try {
  Employee employee = employeeRepository.findById(id).get();
  employee.setFirstName(firstName);
  return new ResponseEntity<Employee>(employeeRepository.save(employee), HttpStatus.OK);
} catch (Exception e) {
  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
}