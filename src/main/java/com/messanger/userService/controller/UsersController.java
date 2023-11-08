package com.messanger.userService.controller;

import com.messanger.userService.models.request.CreateUserRequestModel;
import com.messanger.userService.models.request.LoginRequestModel;
import com.messanger.userService.models.response.SearchUserResponseModel;
import com.messanger.userService.models.response.UserResponseModel;
import com.messanger.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signUp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseModel> registerUser(@RequestBody CreateUserRequestModel createUserRequestModel) {
        UserResponseModel userResponseModel = userService.saveUserDetails(createUserRequestModel);
        System.out.println(userResponseModel.getUserId());
        ResponseEntity<UserResponseModel> userResponseModelResponseEntity = new ResponseEntity<>(userResponseModel,HttpStatus.OK);
        return userResponseModelResponseEntity;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseModel> login(@RequestBody LoginRequestModel loginRequestModel) {
        UserResponseModel userResponseModel = userService.login(loginRequestModel);
        System.out.println(userResponseModel.getUserId());
        ResponseEntity<UserResponseModel> userResponseModelResponseEntity = new ResponseEntity<>(userResponseModel,HttpStatus.OK);
        return userResponseModelResponseEntity;
    }

    @GetMapping(value = "/searchUser", produces = "application/json")
    public ResponseEntity<SearchUserResponseModel> searchUser(@Param("email") String email) {
        SearchUserResponseModel searchUserResponseModel = userService.searchUser(email);
        ResponseEntity<SearchUserResponseModel> searchUserResponseModelResponseEntity = new ResponseEntity<>(searchUserResponseModel,HttpStatus.OK);
        return searchUserResponseModelResponseEntity;
    }


}
