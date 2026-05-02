package com.example.wechat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("messages")
public class Message {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("sender_id")
    private Long senderId;
    
    @TableField("receiver_id")
    private Long receiverId;
    
    @TableField("group_id")
    private Long groupId;
    
    @TableField("content")
    private String content;
    
    @TableField("type")
    private Integer type;
    
    @TableField("status")
    private Integer status;
    
    @TableField("created_at")
    private LocalDateTime createdAt;
}