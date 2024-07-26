package com.example.exercice_session_forum.controller;

import com.example.exercice_session_forum.entity.Utilisateur;
import com.example.exercice_session_forum.service.LoginService;
import com.example.exercice_session_forum.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if(loginService.isLogged()){
            return "homeLogged";
        } else {
            return "login/login";
        }
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("user", new Utilisateur());
        return "login/signin";
    }

    @PostMapping("/signin")
    public String signinSubmit(@ModelAttribute("user") Utilisateur user, @RequestParam("confirmation") String confirmation, Model model) {
        if(user.getPassword().equals(confirmation)){
            userService.createUser(user);
            return "redirect:/login";
        } else {
            return "login/signin";
        }
    }
}
