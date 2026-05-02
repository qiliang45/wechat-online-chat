package com.example.wechat.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SendMessageRequest {
    private Long receiverId;
    
    private Long groupId;
    
    @NotBlank(message = "?????????????")
    private String content;
    
    @NotNull(message = "?????????????")
    private Integer type;
}