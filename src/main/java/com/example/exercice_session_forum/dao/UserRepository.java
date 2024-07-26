package com.example.exercice_session_forum.dao;

import com.example.exercice_session_forum.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Utilisateur, Integer> {
    Utilisateur findByUsername(String username);
}
