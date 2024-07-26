package com.example.exercice_session_forum.dao;

import com.example.exercice_session_forum.entity.Message;
import com.example.exercice_session_forum.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findMessageByUser(Utilisateur user);
}
