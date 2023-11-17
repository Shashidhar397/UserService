package com.messanger.userService.service.es;

import com.messanger.userService.models.entity.es.User;
import com.messanger.userService.models.response.GetUsersResponseModel;
import com.messanger.userService.models.response.SearchUserResponseModel;
import com.messanger.userService.models.response.UserResponseModel;

import java.util.List;

/**
 * @author shashidhar
 */
public interface ESUserService {

    public void saveUser(UserResponseModel userResponseModel);

    public List<SearchUserResponseModel> suggestUsers(String searchTerm);

    GetUsersResponseModel getUsers(List<String> uuids);
}
