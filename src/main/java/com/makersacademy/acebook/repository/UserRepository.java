package com.makersacademy.acebook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.makersacademy.acebook.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String Username);
}