package com.flowsync.services.mappers;

import com.flowsync.dto.UserResponse;
import com.flowsync.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponse convertUserEntityToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setUsername(user.getUsername());
        return userResponse;
    }
}
