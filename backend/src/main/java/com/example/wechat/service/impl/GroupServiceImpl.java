package com.example.wechat.service.impl;

import com.example.wechat.dto.request.CreateGroupRequest;
import com.example.wechat.dto.response.GroupDTO;
import com.example.wechat.dto.response.UserDTO;
import com.example.wechat.entity.Group;
import com.example.wechat.entity.GroupMember;
import com.example.wechat.entity.User;
import com.example.wechat.mapper.GroupMapper;
import com.example.wechat.mapper.GroupMemberMapper;
import com.example.wechat.mapper.UserMapper;
import com.example.wechat.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupMapper groupMapper;
    private final GroupMemberMapper groupMemberMapper;
    private final UserMapper userMapper;
    
    @Override
    @Transactional
    public GroupDTO createGroup(Long userId, CreateGroupRequest request) {
        Group group = new Group();
        group.setName(request.getName());
        group.setCreatedBy(userId);
        groupMapper.insert(group);
        
        GroupMember creator = new GroupMember();
        creator.setGroupId(group.getId());
        creator.setUserId(userId);
        groupMemberMapper.insert(creator);
        
        for (Long memberId : request.getMemberIds()) {
            GroupMember member = new GroupMember();
            member.setGroupId(group.getId());
            member.setUserId(memberId);
            groupMemberMapper.insert(member);
        }
        
        return toDTO(group);
    }
    
    @Override
    public List<GroupDTO> getUserGroups(Long userId) {
        List<Group> groups = groupMapper.findGroupsByUserId(userId);
        return groups.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    @Override
    public GroupDTO getGroupById(Long groupId) {
        Group group = groupMapper.selectById(groupId);
        return group != null ? toDTO(group) : null;
    }
    
    private GroupDTO toDTO(Group group) {
        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setAvatar(group.getAvatar());
        dto.setCreatedAt(group.getCreatedAt());
        
        User creator = userMapper.findById(group.getCreatedBy());
        if (creator != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(creator.getId());
            userDTO.setUsername(creator.getUsername());
            userDTO.setPhone(creator.getPhone());
            userDTO.setAvatar(creator.getAvatar());
            dto.setCreatedBy(userDTO);
        }
        
        List<GroupMember> members = groupMemberMapper.findByGroupId(group.getId());
        List<UserDTO> memberDTOs = new ArrayList<>();
        for (GroupMember member : members) {
            User user = userMapper.findById(member.getUserId());
            if (user != null) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setUsername(user.getUsername());
                userDTO.setPhone(user.getPhone());
                userDTO.setAvatar(user.getAvatar());
                memberDTOs.add(userDTO);
            }
        }
        dto.setMembers(memberDTOs);
        
        return dto;
    }
}