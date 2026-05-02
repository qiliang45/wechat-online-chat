package com.example.wechat.service.impl;

import com.example.wechat.dto.response.FriendRequestResponse;
import com.example.wechat.dto.response.UserDTO;
import com.example.wechat.entity.Friend;
import com.example.wechat.entity.FriendRequest;
import com.example.wechat.entity.User;
import com.example.wechat.mapper.FriendMapper;
import com.example.wechat.mapper.FriendRequestMapper;
import com.example.wechat.mapper.UserMapper;
import com.example.wechat.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRequestMapper friendRequestMapper;
    private final FriendMapper friendMapper;
    private final UserMapper userMapper;
    
    @Override
    @Transactional
    public void sendFriendRequest(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new RuntimeException("????????????????");
        }
        
        if (friendMapper.countFriends(fromUserId, toUserId) > 0) {
            throw new RuntimeException("????????");
        }
        
        FriendRequest existingRequest = friendRequestMapper.findByFromAndTo(fromUserId, toUserId);
        if (existingRequest != null && existingRequest.getStatus() == 0) {
            throw new RuntimeException("?????????????");
        }
        
        FriendRequest request = new FriendRequest();
        request.setFromUserId(fromUserId);
        request.setToUserId(toUserId);
        request.setStatus(0);
        
        friendRequestMapper.insert(request);
    }
    
    @Override
    @Transactional
    public void acceptFriendRequest(Long requestId) {
        FriendRequest request = friendRequestMapper.selectById(requestId);
        if (request == null || request.getStatus() != 0) {
            throw new RuntimeException("?????????????");
        }
        
        request.setStatus(1);
        friendRequestMapper.updateStatus(requestId, 1);
        
        Friend friend1 = new Friend();
        friend1.setUserId(request.getFromUserId());
        friend1.setFriendId(request.getToUserId());
        friend1.setStatus(1);
        friendMapper.insert(friend1);
        
        Friend friend2 = new Friend();
        friend2.setUserId(request.getToUserId());
        friend2.setFriendId(request.getFromUserId());
        friend2.setStatus(1);
        friendMapper.insert(friend2);
    }
    
    @Override
    @Transactional
    public void rejectFriendRequest(Long requestId) {
        FriendRequest request = friendRequestMapper.selectById(requestId);
        if (request == null || request.getStatus() != 0) {
            throw new RuntimeException("?????????????");
        }
        
        request.setStatus(2);
        friendRequestMapper.updateStatus(requestId, 2);
    }
    
    @Override
    public List<FriendRequestResponse> getPendingRequests(Long userId) {
        List<FriendRequest> requests = friendRequestMapper.findPendingRequests(userId);
        return requests.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    private FriendRequestResponse toDTO(FriendRequest request) {
        FriendRequestResponse dto = new FriendRequestResponse();
        dto.setId(request.getId());
        dto.setStatus(request.getStatus());
        dto.setCreatedAt(request.getCreatedAt());
        
        User fromUser = userMapper.findById(request.getFromUserId());
        if (fromUser != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(fromUser.getId());
            userDTO.setUsername(fromUser.getUsername());
            userDTO.setPhone(fromUser.getPhone());
            userDTO.setAvatar(fromUser.getAvatar());
            dto.setFromUser(userDTO);
        }
        
        return dto;
    }
}