package com.makersacademy.acebook.repository;

import org.springframework.data.repository.CrudRepository;

import com.makersacademy.acebook.model.Follower;

public interface FollowerRepository extends CrudRepository<Follower, Long> {
  Follower findByUserId(Long userId);
}