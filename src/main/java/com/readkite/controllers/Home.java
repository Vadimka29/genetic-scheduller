package com.readkite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "user-page";
    }
    @RequestMapping(value = "/error")
    public String getErrorPage(){
        return "error";
    }
}
