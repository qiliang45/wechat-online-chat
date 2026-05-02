package com.example.wechat.service;

import com.example.wechat.dto.request.SendMessageRequest;
import com.example.wechat.dto.response.MessageDTO;

import java.util.List;

public interface MessageService {
    MessageDTO sendMessage(Long senderId, SendMessageRequest request);
    List<MessageDTO> getFriendMessages(Long userId, Long friendId);
    List<MessageDTO> getGroupMessages(Long groupId);
}