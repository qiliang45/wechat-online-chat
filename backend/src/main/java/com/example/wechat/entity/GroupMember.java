package com.example.wechat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("group_members")
public class GroupMember {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("group_id")
    private Long groupId;
    
    @TableField("user_id")
    private Long userId;
    
    @TableField("joined_at")
    private LocalDateTime joinedAt;
}