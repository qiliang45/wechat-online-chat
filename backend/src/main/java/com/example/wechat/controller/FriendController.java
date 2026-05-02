package com.example.wechat.controller;

import com.example.wechat.dto.request.FriendRequestDTO;
import com.example.wechat.dto.response.FriendRequestResponse;
import com.example.wechat.service.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;
    
    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam Long userId, @Valid @RequestBody FriendRequestDTO request) {
        friendService.sendFriendRequest(userId, request.getTargetUserId());
        return ResponseEntity.ok("????????");
    }
    
    @PostMapping("/request/{requestId}/accept")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Long requestId) {
        friendService.acceptFriendRequest(requestId);
        return ResponseEntity.ok("????????????");
    }
    
    @PostMapping("/request/{requestId}/reject")
    public ResponseEntity<String> rejectFriendRequest(@PathVariable Long requestId) {
        friendService.rejectFriendRequest(requestId);
        return ResponseEntity.ok("????????????");
    }
    
    @GetMapping("/requests")
    public ResponseEntity<List<FriendRequestResponse>> getPendingRequests(@RequestParam Long userId) {
        List<FriendRequestResponse> requests = friendService.getPendingRequests(userId);
        return ResponseEntity.ok(requests);
    }
}