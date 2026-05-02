package com.example.wechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wechat.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper extends BaseMapper<Group> {
    @Select("SELECT g.* FROM groups g " +
            "JOIN group_members gm ON g.id = gm.group_id " +
            "WHERE gm.user_id = #{userId}")
    List<Group> findGroupsByUserId(@Param("userId") Long userId);
}