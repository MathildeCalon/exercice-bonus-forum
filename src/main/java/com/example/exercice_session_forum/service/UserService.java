package com.example.exercice_session_forum.service;

import com.example.exercice_session_forum.dao.UserRepository;
import com.example.exercice_session_forum.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Utilisateur getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Utilisateur getById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<Utilisateur> getAllUsers() {
        return (List<Utilisateur>) userRepository.findAll();
    }

    public Utilisateur createUser(Utilisateur user) {
        boolean exists = userRepository.existsById(user.getId());
        if (exists) {
            return null;
        } else {
            return userRepository.save(user);
        }
    }
}
