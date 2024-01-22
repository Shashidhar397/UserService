package com.messanger.userService.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.messanger.userService.models.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shashidhar
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponseModel {
    private String uuid;
    private String name;
    private String description;
    private UserResponseModel createdBy;
    private UserResponseModel updatedBy;
    private List<GroupUsersResponseModel> groupUsers;

    public GroupResponseModel(Group group) {
        this.uuid = group.getUuid();
        this.name = group.getName();
        this.description = group.getDescription();
        this.createdBy = group.getCreatedBy() == null ? null : UserResponseModel.getUserResponseModel(group.getCreatedBy());
        this.updatedBy = group.getUpdatedBy() == null ? null : UserResponseModel.getUserResponseModel(group.getUpdatedBy());
        this.groupUsers = GroupUsersResponseModel.getGroupUsersResponseModel(group);
    }
}
