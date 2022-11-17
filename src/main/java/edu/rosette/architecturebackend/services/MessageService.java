package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.MessageDto;
import edu.rosette.architecturebackend.mappers.MessageMapper;
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

    MessageMapper messageMapper;
    UserRepository userRepository;

    public long addMessage(MessageDto messageDto) {
        var message = messageMapper.messageDtoToMessage(messageDto);
        return messageRepository.save(message).getId();
    }

    public List<MessageDto> getAllMessagesBetweenAuthenticatedUserAndUser(long userId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticatedUser = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new ArrayList<>();
        }
        return messageRepository.findAllBetweenUsers(authenticatedUser, user.get()).stream().map(messageMapper::messageToMessageDto).toList();
    }
}
