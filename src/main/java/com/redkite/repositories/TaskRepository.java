package com.redkite.repositories;


import com.redkite.entities.User;
import org.springframework.data.repository.Repository;

public interface TaskRepository extends Repository<User, Long> {
//    public List<Task> findByUser(User user);
}
