package com.messanger.userService.service;

import com.messanger.userService.models.entity.User;
import com.messanger.userService.models.request.CreateUserRequestModel;
import com.messanger.userService.models.request.LoginRequestModel;
import com.messanger.userService.models.response.SearchUserResponseModel;
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
    public UserResponseModel saveUserDetails(CreateUserRequestModel createUserRequestModel) {
        User user = this.userRepository.save(createUserRequestModel.getUserEntity());
        return UserResponseModel.getUserResponseModel(user);
    }

    @Override
    public UserResponseModel login(LoginRequestModel loginRequestModel) {
        User user = this.userRepository.findUserByEmailAndPassword(loginRequestModel.getUserName(), loginRequestModel.getPassword());
        return UserResponseModel.getUserResponseModel(user);
    }

    @Override
    public SearchUserResponseModel searchUser(String email) {
        User user = this.userRepository.findUserByEmail(email);
        return user != null ? new SearchUserResponseModel(user.getName(), user.getEmail(), user.getId()) : null;
    }
}
