package com.messanger.userService.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.messanger.userService.models.entity.Group;
import com.messanger.userService.models.entity.Role;
import com.messanger.userService.models.entity.Status;
import com.messanger.userService.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shashidhar
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GroupUsersResponseModel {
    private UserResponseModel user;
    private Role role;
    private Status status;

    public GroupUsersResponseModel(User user, Role role, Status status) {
        this.user = UserResponseModel.getUserResponseModel(user);
        this.role = role;
        this.status = status;
    }

    public static List<GroupUsersResponseModel> getGroupUsersResponseModel(Group group) {
        List<GroupUsersResponseModel> groupUsersResponseModels = new ArrayList<>();
        group.getGroupUsers().forEach(groupUser -> {
            groupUsersResponseModels.add(new GroupUsersResponseModel(groupUser.getUser(), groupUser.getRole(), groupUser.getStatus()));
        });
        return groupUsersResponseModels;
    }
}
