package com.example.wechat.service;

import com.example.wechat.dto.request.LoginRequest;
import com.example.wechat.dto.request.RegisterRequest;
import com.example.wechat.dto.response.LoginResponse;
import com.example.wechat.dto.response.UserDTO;
import com.example.wechat.entity.User;

import java.util.List;

public interface UserService {
    UserDTO register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    UserDTO getUserById(Long id);
    User getEntityById(Long id);
    List<UserDTO> searchUsers(String keyword);
    List<UserDTO> getFriends(Long userId);
}