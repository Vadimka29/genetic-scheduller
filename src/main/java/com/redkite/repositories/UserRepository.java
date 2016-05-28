package com.redkite.repositories;

import com.redkite.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository  extends CrudRepository<User, Long> {
    User findByLogin(String login);
}