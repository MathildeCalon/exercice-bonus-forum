package com.example.exercice_session_forum.service;

import com.example.exercice_session_forum.entity.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private HttpSession session;

    public boolean login(String username, String password) {
        if (userService.getUserByUsername(username) == null || !userService.getUserByUsername(username).getPassword().equals(password)) {
            return false;
        } else {
            session.setAttribute("logged", true);
            session.setAttribute("userId", userService.getUserByUsername(username).getId());
            return true;
        }
    }

    public boolean isLogged(){
        return session.getAttribute("logged") != null;
    }

    public Utilisateur getUserBySession(){
        int userId = (int) session.getAttribute("userId");
        System.out.println(session.getAttribute("userId"));
        System.out.println(userService.getById(userId));
        return userService.getById(userId);
    }

    public void logout() {
        session.setAttribute("logged", false);
    }
}
