package com.example.wechat.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FriendRequestDTO {
    @NotNull(message = "??????ID???????")
    private Long targetUserId;
}