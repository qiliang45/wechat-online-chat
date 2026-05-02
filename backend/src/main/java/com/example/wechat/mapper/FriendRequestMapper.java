package com.example.wechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wechat.entity.FriendRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FriendRequestMapper extends BaseMapper<FriendRequest> {
    @Select("SELECT * FROM friend_requests WHERE from_user_id = #{fromUserId} AND to_user_id = #{toUserId}")
    FriendRequest findByFromAndTo(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
    
    @Select("SELECT * FROM friend_requests WHERE to_user_id = #{toUserId} AND status = 0")
    List<FriendRequest> findPendingRequests(@Param("toUserId") Long toUserId);
    
    @Update("UPDATE friend_requests SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}