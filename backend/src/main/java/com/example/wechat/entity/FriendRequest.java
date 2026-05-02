package com.example.wechat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("friend_requests")
public class FriendRequest {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("from_user_id")
    private Long fromUserId;
    
    @TableField("to_user_id")
    private Long toUserId;
    
    @TableField("status")
    private Integer status;
    
    @TableField("created_at")
    private LocalDateTime createdAt;
}