package com.example.wechat.service.impl;

import com.example.wechat.dto.request.LoginRequest;
import com.example.wechat.dto.request.RegisterRequest;
import com.example.wechat.dto.response.LoginResponse;
import com.example.wechat.dto.response.UserDTO;
import com.example.wechat.entity.User;
import com.example.wechat.mapper.UserMapper;
import com.example.wechat.service.UserService;
import com.example.wechat.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    @Override
    @Transactional
    public UserDTO register(RegisterRequest request) {
        if (userMapper.findByPhone(request.getPhone()) != null) {
            throw new RuntimeException("???????????");
        }
        
        User user = new User();
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        userMapper.insert(user);
        return toDTO(user);
    }
    
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByPhone(request.getPhone());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("?????????????");
        }
        
        String token = jwtUtil.generateToken(user.getId());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUser(toDTO(user));
        return response;
    }
    
    @Override
    public UserDTO getUserById(Long id) {
        User user = userMapper.findById(id);
        return user != null ? toDTO(user) : null;
    }
    
    @Override
    public User getEntityById(Long id) {
        return userMapper.findById(id);
    }
    
    @Override
    public List<UserDTO> searchUsers(String keyword) {
        List<User> users = userMapper.searchUsers(keyword);
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<UserDTO> getFriends(Long userId) {
        List<User> friends = userMapper.findFriends(userId);
        return friends.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setPhone(user.getPhone());
        dto.setUsername(user.getUsername());
        dto.setAvatar(user.getAvatar());
        return dto;
    }
}