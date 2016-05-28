package com.redkite.services.impl;

import com.redkite.entities.Task;
import com.redkite.entities.User;
import com.redkite.repositories.TaskRepository;
import com.redkite.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }

    @Override
    public Task findByTaskName(String name) {
        return taskRepository.findByTaskName(name);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }
}
