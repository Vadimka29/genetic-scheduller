package com.redkite.repositories;

import com.redkite.entities.User;

/**
 * Created by Vadym on 20.03.2016.
 */
//TODO: Make to use Spring Data Repository
public interface UserRepository /*extends Repository<UserEntity, Long>*/ {
    User findByLogin(String login);
}