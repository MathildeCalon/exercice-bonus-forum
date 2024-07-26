package com.example.exercice_session_forum.controller;

import com.example.exercice_session_forum.entity.Message;
import com.example.exercice_session_forum.service.LoginService;
import com.example.exercice_session_forum.service.MessageService;
import com.example.exercice_session_forum.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final LoginService loginService;

    @Autowired
    HttpSession session;

    public MessageController(MessageService messageService, LoginService loginService) {
        this.messageService = messageService;
        this.loginService = loginService;
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        if (loginService.isLogged()) {
            model.addAttribute("messages", messageService.getAllMessages());
            return "messageList";
        } else {
            return "login/login";
        }
    }

    @GetMapping("/addmessage")
    public String addMessage(Model model) {
        if (loginService.isLogged()) {
            model.addAttribute("message", new Message());
            return "addMessage";
        } else {
            return "login/login";
        }
    }

    @PostMapping("/addmessage")
    public String addMessage(@ModelAttribute("message") Message message, Model model) {
        if (loginService.isLogged()) {
            message.setTime(LocalDateTime.now());
            System.out.println(loginService.getUserBySession());
            message.setUser(loginService.getUserBySession());
            messageService.createMessage(message);
            return "redirect:/messages";
        } else {
            return "login/login";
        }
    }
}
