package com.readkite.repositories;

import com.readkite.entities.UserEntity;
import org.springframework.data.repository.Repository;

/**
 * Created by Vadym on 20.03.2016.
 */
//TODO: Make to use Spring Data Repository
public interface UserRepository /*extends Repository<UserEntity, Long>*/ {
    UserEntity findByLogin(String login);
}