package com.example.wechat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "???????????")
    private String phone;
    
    @NotBlank(message = "?????????")
    private String password;
}