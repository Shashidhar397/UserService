package com.messanger.userService.service;

import com.messanger.userService.models.request.CreateUserRequestModel;
import com.messanger.userService.models.request.LoginRequestModel;
import com.messanger.userService.models.response.SearchUserResponseModel;
import com.messanger.userService.models.response.UserResponseModel;

public interface UserService {

    public UserResponseModel saveUserDetails(CreateUserRequestModel createUserRequestModel);

    public UserResponseModel login(LoginRequestModel loginRequestModel);

    public SearchUserResponseModel searchUser(String searchTerm);

}
