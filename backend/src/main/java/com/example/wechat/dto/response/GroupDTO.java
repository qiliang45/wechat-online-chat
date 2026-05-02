package com.example.wechat.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GroupDTO {
    private Long id;
    private String name;
    private String avatar;
    private UserDTO createdBy;
    private List<UserDTO> members;
    private LocalDateTime createdAt;
}