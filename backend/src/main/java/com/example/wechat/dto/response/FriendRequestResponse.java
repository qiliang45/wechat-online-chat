package com.example.wechat.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendRequestResponse {
    private Long id;
    private UserDTO fromUser;
    private Integer status;
    private LocalDateTime createdAt;
}