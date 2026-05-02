package com.example.wechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wechat.entity.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper extends BaseMapper<Friend> {
    @Select("SELECT * FROM friends WHERE user_id = #{userId} AND friend_id = #{friendId}")
    Friend findByUserAndFriend(@Param("userId") Long userId, @Param("friendId") Long friendId);
    
    @Select("SELECT COUNT(*) FROM friends WHERE (user_id = #{userId} AND friend_id = #{friendId}) OR (user_id = #{friendId} AND friend_id = #{userId})")
    int countFriends(@Param("userId") Long userId, @Param("friendId") Long friendId);
}