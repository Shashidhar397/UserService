package com.messanger.userService.service;

import com.messanger.userService.models.request.CreateGroupRequestModel;
import com.messanger.userService.models.request.UpdateUserToGroupRequestModel;
import com.messanger.userService.models.response.GroupResponseModel;

import java.util.List;

/**
 * @author shashidhar
 */
public interface GroupService {

    void createGroup(CreateGroupRequestModel createGroupRequestModel);

    void addUserToGroup(UpdateUserToGroupRequestModel updateUserToGroupRequestModel, String groupUuid);

    List<GroupResponseModel> groups();

}
