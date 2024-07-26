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

@Controller
public class MessageController {
    private final MessageService messageService;
    private final LoginService loginService;
    private final UserService userService;

    @Autowired
    HttpSession session;

    public MessageController(MessageService messageService, LoginService loginService,UserService userService) {
        this.messageService = messageService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messages", messageService.getAllMessages());
        return "messageList";
    }

    @GetMapping("/addmessage")
    public String addMessage(Model model) {
        model.addAttribute("message", new Message());
        return "addMessage";
    }

    @PostMapping("/addmessage")
    public String addMessage(@ModelAttribute("message") Message message, Model model) {
        message.setTime("heure par défaut");
        System.out.println(loginService.getUserBySession());
        message.setUser(loginService.getUserBySession());
        messageService.createMessage(message);
        return "redirect:/messages";
    }

}
