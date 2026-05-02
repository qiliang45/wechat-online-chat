package com.example.wechat.controller;

import com.example.wechat.dto.request.CreateGroupRequest;
import com.example.wechat.dto.response.GroupDTO;
import com.example.wechat.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    
    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestParam Long userId, @Valid @RequestBody CreateGroupRequest request) {
        GroupDTO group = groupService.createGroup(userId, request);
        return ResponseEntity.ok(group);
    }
    
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getUserGroups(@RequestParam Long userId) {
        List<GroupDTO> groups = groupService.getUserGroups(userId);
        return ResponseEntity.ok(groups);
    }
    
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long groupId) {
        GroupDTO group = groupService.getGroupById(groupId);
        return ResponseEntity.ok(group);
    }
}