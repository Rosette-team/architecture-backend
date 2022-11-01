package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.User;
import edu.rosette.architecturebackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String username) {
        return userRepository.findUserByUsername(username);
    }
}
