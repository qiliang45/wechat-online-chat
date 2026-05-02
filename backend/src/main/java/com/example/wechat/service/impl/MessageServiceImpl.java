package com.example.wechat.service.impl;

import com.example.wechat.dto.request.SendMessageRequest;
import com.example.wechat.dto.response.MessageDTO;
import com.example.wechat.dto.response.UserDTO;
import com.example.wechat.entity.Message;
import com.example.wechat.entity.User;
import com.example.wechat.mapper.MessageMapper;
import com.example.wechat.mapper.UserMapper;
import com.example.wechat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;
    
    @Override
    @Transactional
    public MessageDTO sendMessage(Long senderId, SendMessageRequest request) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(request.getReceiverId());
        message.setGroupId(request.getGroupId());
        message.setContent(request.getContent());
        message.setType(request.getType());
        message.setStatus(1);
        
        messageMapper.insert(message);
        return toDTO(message);
    }
    
    @Override
    public List<MessageDTO> getFriendMessages(Long userId, Long friendId) {
        List<Message> messages = messageMapper.findFriendMessages(userId, friendId);
        return messages.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<MessageDTO> getGroupMessages(Long groupId) {
        List<Message> messages = messageMapper.findGroupMessages(groupId);
        return messages.stream().map(this::toDTO).collect(Collectors.toList());
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
}