package com.redkite.services;

import com.redkite.entities.User;


public interface UserService {
    User getUserByLogin(String login);
}
