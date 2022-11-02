package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.Message;
import edu.rosette.architecturebackend.repositories.MessageRepository;
import edu.rosette.architecturebackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MessageService {
    MessageRepository messageRepository;
    UserRepository userRepository;

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getMessages(long receiverId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var sender = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        var receiver = userRepository.findById(receiverId);
        if (receiver.isEmpty()) {
            return new ArrayList<>();
        }
        return messageRepository.findAllBySenderAndReceiver(sender, receiver.get());
    }
}
