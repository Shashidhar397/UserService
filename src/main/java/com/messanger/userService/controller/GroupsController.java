package com.messanger.userService.controller;

import com.messanger.userService.models.request.CreateGroupRequestModel;
import com.messanger.userService.models.request.UpdateUserToGroupRequestModel;
import com.messanger.userService.models.response.GroupResponseModel;
import com.messanger.userService.service.GroupService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shashidhar
 */
@RestController
@RequestMapping("/groups")
@Slf4j
public class GroupsController {

    private final GroupService groupService;

    public GroupsController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<GroupResponseModel>> groups() {
        List<GroupResponseModel> searchUserResponseModels = this.groupService.groups();
        return new ResponseEntity<>(searchUserResponseModels, HttpStatus.OK);
    }

    @PostMapping(value = "/createGroup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequestModel createGroupRequestModel) {
        this.groupService.createGroup(createGroupRequestModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{group_id}/addUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addUserToGroup(@PathVariable("group_id") String groupId, @RequestBody @Valid UpdateUserToGroupRequestModel updateUserToGroupRequestModel) {
        log.info("groupId:"+groupId);
        this.groupService.addUserToGroup(updateUserToGroupRequestModel, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
