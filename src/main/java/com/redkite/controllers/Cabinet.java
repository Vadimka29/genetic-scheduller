package com.redkite.controllers;

import com.redkite.entities.Task;
import com.redkite.entities.User;
import com.redkite.services.TaskService;
import com.redkite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//TODO refactor
@Controller
@RequestMapping("/cabinet")
public class Cabinet {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/generate")
    public ResponseEntity<String> generateSchedule(@RequestBody List<Task> tasks, ModelMap model) {
        User user = getCurrUser();
        tasks.forEach(t -> {
            t.setUser(user);
            //TODO refactor find better solution
            // check if not exist
            if(taskService.findByTaskName(t.getTaskName()) == null) {
                taskService.save(t);
            }
        });
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


    @RequestMapping("/load")
    @ResponseBody
    public List<Task> generateSchedule(ModelMap model) {
        List<Task> tasks = taskService.findByUser(getCurrUser());
        return tasks != null ? tasks : new ArrayList<>();
    }

    public User getCurrUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        return userService.getUserByLogin(login);
    }
}
