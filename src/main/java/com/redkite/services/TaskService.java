package com.redkite.services;


import com.redkite.entities.Task;
import com.redkite.entities.User;

import java.util.List;

public interface TaskService {
    public List<Task> findByUser(User user);
    public Task findByTaskName(String name);
    public Task save(Task task);
}
