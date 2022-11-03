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

    public void addMessage(MessageDto messageDto) {
        var message = messageMapper.messageDtoToMessage(messageDto);
        messageRepository.save(message);
    }

    public List<MessageDto> getMessages(long receiverId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var sender = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        var receiver = userRepository.findById(receiverId);
        if (receiver.isEmpty()) {
            return new ArrayList<>();
        }
        return messageRepository.findAllBySenderAndReceiver(sender, receiver.get()).stream().map(message -> messageMapper.messageToMessageDto(message)).toList();
    }
}
