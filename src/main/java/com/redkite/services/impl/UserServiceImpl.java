package com.redkite.services.impl;

import com.redkite.entities.User;
import com.redkite.repositories.UserRepository;
import com.redkite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return user;
    }
}
