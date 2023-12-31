package com.org.userservices.service;

import com.org.userservices.entity.User;
import com.org.userservices.service.dto.ResponseDTO;

public interface UserService {
    User saveUser(User user);
    ResponseDTO getUser(Long userId);
}
