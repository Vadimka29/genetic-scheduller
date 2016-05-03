package com.redkite.repositories;

import com.redkite.entities.User;
import org.springframework.data.repository.Repository;


public interface UserRepository extends Repository<User, Long> {
    User findByLogin(String login);
}