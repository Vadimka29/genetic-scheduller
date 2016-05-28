package com.redkite.controllers;

import com.redkite.entities.User;
import com.redkite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Home {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index", "/home"})
    public String getHome() {
        return "index";
    }

    @RequestMapping(value = "/admin")
    public String getAdminPage() {
        return "/admin/admin-page";
    }

    @RequestMapping(value = "/calendar")
    public String getUserPage() {
        return "calendar";
    }

    @RequestMapping(value = "/error")
    public String getErrorPage() {
        return "error";
    }

    @RequestMapping(value = "/registration")
    public String registration(@ModelAttribute User user) { //TODO read about ModelAttribute
        user.setRole("USER");
        userService.save(user);
        return "index";
    }

//    @RequestMapping(value = "/api/stub/chart")
//    public @ResponseBody String getChart() throws IOException {
//        String json = IOUtils.toString(new InputStreamReader(Home.class.getClassLoader().getResourceAsStream("stub.json")));
//        return json;
//    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
