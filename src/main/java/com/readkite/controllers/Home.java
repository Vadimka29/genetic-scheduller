package com.readkite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home {

    @RequestMapping(value = {"/", "/home"})
    public String getHome(@RequestParam(name = "name", defaultValue = "Vadym")String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
