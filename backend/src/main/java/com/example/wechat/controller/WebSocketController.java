package com.example.wechat.controller;

import com.example.wechat.dto.response.MessageDTO;
import com.example.wechat.dto.response.UserDTO;
import com.example.wechat.entity.Message;
import com.example.wechat.entity.User;
import com.example.wechat.mapper.MessageMapper;
import com.example.wechat.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;
    
    @MessageMapping("/chat/send")
    public void sendMessage(MessageRequest request) {
        Message message = new Message();
        message.setSenderId(request.getSenderId());
        message.setReceiverId(request.getReceiverId());
        message.setGroupId(request.getGroupId());
        message.setContent(request.getContent());
        message.setType(request.getType());
        message.setStatus(1);
        
        messageMapper.insert(message);
        
        MessageDTO dto = toDTO(message);
        
        if (request.getGroupId() != null) {
            messagingTemplate.convertAndSend("/topic/group/" + request.getGroupId(), dto);
        } else if (request.getReceiverId() != null) {
            messagingTemplate.convertAndSend("/topic/user/" + request.getReceiverId(), dto);
        }
    }
    
    private MessageDTO toDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setReceiverId(message.getReceiverId());
        dto.setGroupId(message.getGroupId());
        dto.setContent(message.getContent());
        dto.setType(message.getType());
        dto.setStatus(message.getStatus());
        dto.setCreatedAt(message.getCreatedAt());
        
        User sender = userMapper.findById(message.getSenderId());
        if (sender != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(sender.getId());
            userDTO.setUsername(sender.getUsername());
            userDTO.setPhone(sender.getPhone());
            userDTO.setAvatar(sender.getAvatar());
            dto.setSender(userDTO);
        }
        
        return dto;
    }
    
    public static class MessageRequest {
        private Long senderId;
        private Long receiverId;
        private Long groupId;
        private String content;
        private Integer type;
        
        public Long getSenderId() { return senderId; }
        public void setSenderId(Long senderId) { this.senderId = senderId; }
        public Long getReceiverId() { return receiverId; }
        public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
        public Long getGroupId() { return groupId; }
        public void setGroupId(Long groupId) { this.groupId = groupId; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public Integer getType() { return type; }
        public void setType(Integer type) { this.type = type; }
    }
}