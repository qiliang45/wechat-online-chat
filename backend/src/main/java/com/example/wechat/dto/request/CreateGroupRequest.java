package com.example.wechat.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequest {
    @NotBlank(message = "???????????")
    private String name;
    
    @NotEmpty(message = "???????????")
    private List<Long> memberIds;
}