package com.example.wechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wechat.entity.GroupMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMemberMapper extends BaseMapper<GroupMember> {
    @Select("SELECT * FROM group_members WHERE group_id = #{groupId}")
    List<GroupMember> findByGroupId(@Param("groupId") Long groupId);
    
    @Select("SELECT COUNT(*) FROM group_members WHERE group_id = #{groupId} AND user_id = #{userId}")
    int countByGroupAndUser(@Param("groupId") Long groupId, @Param("userId") Long userId);
}