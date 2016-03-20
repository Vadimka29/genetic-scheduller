package com.readkite.services.impl;

import com.readkite.entities.UserEntity;
import com.readkite.repositories.UserRepository;
import com.readkite.repositories.impl.UserRepositoryImpl;
import com.readkite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Vadym on 20.03.2016.
 */
@Service
public class BasicUserService implements UserService {
    //TODO: Change to the @Autowired
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public UserEntity getUserByLogin(String login) {
        UserEntity userEntity = userRepository.findByLogin(login);
        return userEntity;
    }
}
