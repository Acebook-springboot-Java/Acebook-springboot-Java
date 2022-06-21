package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface UserRepository extends CrudRepository<User, Long> {
    @CrossOrigin(origins = "null"); //env variable

}
