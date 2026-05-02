package com.example.wechat.service;

import com.example.wechat.dto.request.CreateGroupRequest;
import com.example.wechat.dto.response.GroupDTO;

import java.util.List;

public interface GroupService {
    GroupDTO createGroup(Long userId, CreateGroupRequest request);
    List<GroupDTO> getUserGroups(Long userId);
    GroupDTO getGroupById(Long groupId);
}