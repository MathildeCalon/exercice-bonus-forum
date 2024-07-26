package com.example.exercice_session_forum.service;

import com.example.exercice_session_forum.dao.MessageRepository;
import com.example.exercice_session_forum.entity.Message;
import com.example.exercice_session_forum.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public List<Message> getMessagesBySender(Utilisateur user) {
        return (List<Message>) messageRepository.findMessageByUser(user);
    }
}
