package edu.rosette.architecturebackend.mappers;

import edu.rosette.architecturebackend.datatransfer.MessageDto;
import edu.rosette.architecturebackend.models.Message;
import edu.rosette.architecturebackend.repositories.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class MessageMapper {
    @Autowired
    UserRepository userRepository;

    public abstract Message messageDtoToMessage(MessageDto messageDto);

    @AfterMapping
    protected void afterMessageDtoToMessage(MessageDto messageDto, @MappingTarget Message message) {
        var sender = userRepository.findById(messageDto.getSenderId());
        if (sender.isEmpty()) {
            throw new RuntimeException("Try to assign user with id %d but it's not exits".formatted(messageDto.getSenderId()));
        }
        var receiver = userRepository.findById(messageDto.getReceiverId());
        if (receiver.isEmpty()) {
            throw new RuntimeException("Try to assign user with id %d but it's not exits".formatted(messageDto.getSenderId()));
        }
        message.setSender(sender.get());
        message.setReceiver(receiver.get());
    }

    @InheritInverseConfiguration(name = "messageDtoToMessage")
    public abstract MessageDto messageToMessageDto(Message message);

    @AfterMapping
    protected void afterMessageToMessageDto(Message message, @MappingTarget MessageDto messageDto) {
        messageDto.setSenderId(message.getSender().getId());
        messageDto.setReceiverId(message.getReceiver().getId());
    }

    @InheritConfiguration(name = "messageDtoToMessage")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract Message updateMessageFromMessageDto(MessageDto messageDto, @MappingTarget Message message);
}
