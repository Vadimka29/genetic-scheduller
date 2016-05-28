package com.redkite.repositories;


import com.redkite.entities.Task;
import com.redkite.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    public List<Task> findByUser(User user);
    public Task findByTaskName(String name);
}
