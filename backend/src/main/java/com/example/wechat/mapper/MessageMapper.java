package com.example.wechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wechat.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    @Select("SELECT * FROM messages WHERE (sender_id = #{userId} AND receiver_id = #{friendId}) OR (sender_id = #{friendId} AND receiver_id = #{userId}) ORDER BY created_at ASC")
    List<Message> findFriendMessages(@Param("userId") Long userId, @Param("friendId") Long friendId);
    
    @Select("SELECT * FROM messages WHERE group_id = #{groupId} ORDER BY created_at ASC")
    List<Message> findGroupMessages(@Param("groupId") Long groupId);
}