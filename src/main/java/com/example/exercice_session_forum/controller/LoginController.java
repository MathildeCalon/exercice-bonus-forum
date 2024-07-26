package com.example.exercice_session_forum.controller;

import com.example.exercice_session_forum.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password) {
        if(loginService.login(username, password)){
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        loginService.logout();
        return "redirect:/";
    }
}
