package com.readkite.repositories.impl;

import com.readkite.entities.UserEntity;
import com.readkite.repositories.UserRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadym on 20.03.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity findByLogin(String login) {
        if("admin".equals(login)){
            UserEntity admin = new UserEntity();
            admin.setLogin("admin");
            admin.setPassword("admin");
            return admin;
        }
        if("user".equals(login)){
            UserEntity user = new UserEntity();
            user.setLogin("user");
            user.setPassword("user");
            return user;
        }
        return null;
    }
}
