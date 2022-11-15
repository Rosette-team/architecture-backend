package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.datatransfer.MessageDto;
import edu.rosette.architecturebackend.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("message")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<?> addMessage(@RequestBody MessageDto messageDto) {
        messageService.addMessage(messageDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{receiverId}")
    public ResponseEntity<?> getMessages(@PathVariable long receiverId) {
        var messages = messageService.getAllMessagesBetweenAuthenticatedUserAndUser(receiverId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
