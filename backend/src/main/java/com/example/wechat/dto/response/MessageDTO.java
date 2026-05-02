package com.example.wechat.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private Long id;
    private UserDTO sender;
    private Long receiverId;
    private Long groupId;
    private String content;
    private Integer type;
    private Integer status;
    private LocalDateTime createdAt;
}