package com.makersacademy.acebook.repository;

import org.springframework.data.repository.CrudRepository;

import com.makersacademy.acebook.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String Username);
}