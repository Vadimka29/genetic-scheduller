package com.readkite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home {

    @RequestMapping(value = {"/", "/index", "/home"})
    public String getHome() {
        return "index";
    }
    @RequestMapping(value = "/admin")
    public String getAdminPage(){
        return "/admin/admin-page";
    }

    @RequestMapping(value = "/user")
    public String getUserPage(){
        return "user";
    }
    @RequestMapping(value = "/error")
    public String getErrorPage(){
        return "error";
    }
}
