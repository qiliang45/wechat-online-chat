package com.example.wechat.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private UserDTO user;
}