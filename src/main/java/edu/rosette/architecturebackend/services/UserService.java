package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.UserDto;
import edu.rosette.architecturebackend.mappers.UserMapper;
import edu.rosette.architecturebackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    public UserDto getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        return userMapper.userToUserDto(user);
    }
}
