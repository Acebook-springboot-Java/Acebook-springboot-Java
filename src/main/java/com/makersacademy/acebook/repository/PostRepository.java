package com.makersacademy.acebook.repository;

import org.springframework.data.repository.CrudRepository;

import com.makersacademy.acebook.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
  Post findById(int id);

}
