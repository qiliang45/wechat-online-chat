package com.example.wechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wechat.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM users WHERE phone = #{phone}")
    User findByPhone(@Param("phone") String phone);
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Long id);
    
    @Select("SELECT u.* FROM users u " +
            "WHERE u.id IN (SELECT f.friend_id FROM friends f WHERE f.user_id = #{userId} AND f.status = 1) " +
            "OR u.id IN (SELECT f.user_id FROM friends f WHERE f.friend_id = #{userId} AND f.status = 1)")
    List<User> findFriends(@Param("userId") Long userId);
    
    @Select("SELECT * FROM users WHERE username LIKE CONCAT('%', #{keyword}, '%') OR phone LIKE CONCAT('%', #{keyword}, '%')")
    List<User> searchUsers(@Param("keyword") String keyword);
}