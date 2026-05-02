package com.example.wechat.service;

import com.example.wechat.dto.response.FriendRequestResponse;

import java.util.List;

public interface FriendService {
    void sendFriendRequest(Long fromUserId, Long toUserId);
    void acceptFriendRequest(Long requestId);
    void rejectFriendRequest(Long requestId);
    List<FriendRequestResponse> getPendingRequests(Long userId);
}