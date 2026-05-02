package com.example.wechat.controller;

import com.example.wechat.dto.request.SendMessageRequest;
import com.example.wechat.dto.response.MessageDTO;
import com.example.wechat.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    
    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(@RequestParam Long senderId, @Valid @RequestBody SendMessageRequest request) {
        MessageDTO message = messageService.sendMessage(senderId, request);
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/friend")
    public ResponseEntity<List<MessageDTO>> getFriendMessages(@RequestParam Long userId, @RequestParam Long friendId) {
        List<MessageDTO> messages = messageService.getFriendMessages(userId, friendId);
        return ResponseEntity.ok(messages);
    }
    
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<MessageDTO>> getGroupMessages(@PathVariable Long groupId) {
        List<MessageDTO> messages = messageService.getGroupMessages(groupId);
        return ResponseEntity.ok(messages);
    }
}