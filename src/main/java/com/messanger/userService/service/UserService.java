package com.messanger.userService.service;

import com.messanger.userService.models.request.UserRequestModel;
import com.messanger.userService.models.response.UserResponseModel;

public interface UserService {

    public UserResponseModel saveUserDetails(UserRequestModel userRequestModel);

    public UserResponseModel getUser(UserRequestModel userRequestModel);

}
