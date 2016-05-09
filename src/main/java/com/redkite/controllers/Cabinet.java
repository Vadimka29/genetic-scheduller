package com.redkite.controllers;

import com.redkite.entities.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cabinet")
public class Cabinet {

    @RequestMapping("/generate")
    public String generateSchedule(@RequestBody List<Task> tasks, ModelMap model) {
        tasks.forEach(System.out::println);
        model.addAttribute("tasks", tasks);
        return "redirect:/test-schedule";
    }
}
