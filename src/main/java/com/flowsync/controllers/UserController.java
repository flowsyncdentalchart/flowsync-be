package com.flowsync.controllers;

import com.flowsync.dto.UserResponse;
import com.flowsync.models.User;
import com.flowsync.services.UserService;
import com.flowsync.services.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        UserResponse userResponse = userMapper.convertUserEntityToUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
