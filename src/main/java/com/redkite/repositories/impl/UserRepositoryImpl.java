package com.redkite.repositories.impl;

import com.redkite.entities.User;
import com.redkite.repositories.UserRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadym on 20.03.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findByLogin(String login) {
        if("admin".equals(login)){
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword("admin");
            return admin;
        }
        if("user".equals(login)){
            User user = new User();
            user.setLogin("user");
            user.setPassword("user");
            return user;
        }
        return null;
    }
}
