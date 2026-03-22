package com.flowsync.services;

import com.flowsync.dto.UserResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.User;
import com.flowsync.repositories.UserRepository;
import com.flowsync.services.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        UserResponse userResponse = userMapper.convertUserEntityToUserResponse(user);
        return userResponse;

    }
}
