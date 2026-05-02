package com.example.wechat.controller;

import com.example.wechat.dto.response.UserDTO;
import com.example.wechat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String keyword) {
        List<UserDTO> users = userService.searchUsers(keyword);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/friends")
    public ResponseEntity<List<UserDTO>> getFriends(@RequestParam Long userId) {
        List<UserDTO> friends = userService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }
}