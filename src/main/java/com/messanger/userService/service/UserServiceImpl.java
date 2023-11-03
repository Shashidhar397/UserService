package com.messanger.userService.service;

import com.messanger.userService.models.entity.User;
import com.messanger.userService.models.request.UserRequestModel;
import com.messanger.userService.models.response.UserResponseModel;
import com.messanger.userService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseModel saveUserDetails(UserRequestModel userRequestModel) {
        User user = this.userRepository.save(userRequestModel.getUserEntity());
        return UserResponseModel.getUserResponseModel(user);
    }

    @Override
    public UserResponseModel getUser(UserRequestModel userRequestModel) {
        User user = this.userRepository.findUserByContactNumber(userRequestModel.getContactNumber());
        return UserResponseModel.getUserResponseModel(user);
    }
}
