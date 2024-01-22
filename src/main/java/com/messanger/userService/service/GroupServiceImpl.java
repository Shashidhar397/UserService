package com.messanger.userService.service;

import com.messanger.userService.models.entity.*;
import com.messanger.userService.models.request.Action;
import com.messanger.userService.models.request.CreateGroupRequestModel;
import com.messanger.userService.models.request.UpdateUserToGroupRequestModel;
import com.messanger.userService.models.response.GroupResponseModel;
import com.messanger.userService.repository.GroupRepository;
import com.messanger.userService.repository.GroupUserRepository;
import com.messanger.userService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author shashidhar
 */
@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;
    private final GroupUserRepository groupUserRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository, GroupUserRepository groupUserRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.groupUserRepository = groupUserRepository;
    }
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void createGroup(CreateGroupRequestModel createGroupRequestModel) {
        Optional<User> optionalUser = this.userRepository.findByUuid(createGroupRequestModel.getUserUuid());

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        Group group = new Group(createGroupRequestModel, user, user);
        this.groupRepository.save(group);

        GroupUser groupUser = new GroupUser(group, user, Role.ADMIN, Status.ACTIVE);
        this.groupUserRepository.save(groupUser);
    }

    @Override
    public void addUserToGroup(UpdateUserToGroupRequestModel updateUserToGroupRequestModel, String groupUuid) {
        Group group = this.groupRepository.findByUuid(groupUuid);
        if(group == null) {
            throw new RuntimeException("Group not found");
        }
        Optional<User> optionalUser = this.userRepository.findByUuid(updateUserToGroupRequestModel.getUserUuid());

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        Optional<GroupUser> existingGroupUserOptional = this.groupUserRepository.findByGroupAndUser(group, user);
        if (existingGroupUserOptional.isPresent()){
            throw new RuntimeException("User Already present in the group");
        }
        GroupUser groupUser = new GroupUser(group, user, updateUserToGroupRequestModel.getRole(), updateUserToGroupRequestModel.getAction().equals(Action.ADD) ? Status.ACTIVE : Status.INACTIVE);
        this.groupUserRepository.save(groupUser);
    }

    @Override
    public List<GroupResponseModel> groups() {
        List<Group> groups = this.groupRepository.findAll();
        List<GroupResponseModel> groupResponseModels = new ArrayList<>();
        groups.forEach(group -> {
            groupResponseModels.add(new GroupResponseModel(group));
        });
        return groupResponseModels;
    }

}
