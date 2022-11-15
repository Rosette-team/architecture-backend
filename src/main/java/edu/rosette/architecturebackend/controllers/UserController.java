package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("user")
public class UserController {
    UserService userService;

    @GetMapping("login")
    ResponseEntity<?> getAuthenticatedUser() {
        var userDto = userService.getAuthenticatedUser();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
