package com.readkite.services;

import com.readkite.entities.UserEntity;
import org.springframework.context.annotation.Bean;

/**
 * Created by Vadym on 20.03.2016.
 */
public interface UserService {
    UserEntity getUserByLogin(String login);
}
