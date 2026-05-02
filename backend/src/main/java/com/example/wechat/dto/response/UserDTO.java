package com.example.wechat.dto.response;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String phone;
    private String username;
    private String avatar;
}