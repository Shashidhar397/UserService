package com.messanger.userService.controller;

import com.messanger.userService.models.request.UserRequestModel;
import com.messanger.userService.models.response.UserResponseModel;
import com.messanger.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseModel> registerUser(@RequestBody UserRequestModel userRequestModel) {
        UserResponseModel userResponseModel = userService.saveUserDetails(userRequestModel);
        System.out.println(userResponseModel.getUserId());
        ResponseEntity<UserResponseModel> userResponseModelResponseEntity = new ResponseEntity<>(userResponseModel,HttpStatus.OK);
        return userResponseModelResponseEntity;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseModel> login(@RequestBody UserRequestModel userRequestModel) {
        UserResponseModel userResponseModel = userService.getUser(userRequestModel);
        System.out.println(userResponseModel.getUserId());
        ResponseEntity<UserResponseModel> userResponseModelResponseEntity = new ResponseEntity<>(userResponseModel,HttpStatus.OK);
        return userResponseModelResponseEntity;
    }


}
