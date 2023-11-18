package com.messanger.userService.controller;

import com.messanger.userService.models.request.CreateUserRequestModel;
import com.messanger.userService.models.request.GetUsersRequestModel;
import com.messanger.userService.models.request.LoginRequestModel;
import com.messanger.userService.models.response.GetUsersResponseModel;
import com.messanger.userService.models.response.SearchUserResponseModel;
import com.messanger.userService.models.response.UserResponseModel;
import com.messanger.userService.service.UserService;
import com.messanger.userService.service.es.ESUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private ESUserService esUserService;

    @PostMapping(value = "/signUp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseModel> registerUser(@RequestBody CreateUserRequestModel createUserRequestModel) {
        UserResponseModel userResponseModel = userService.saveUserDetails(createUserRequestModel);
        if(userResponseModel != null) {
            this.esUserService.saveUser(userResponseModel);
            System.out.println(userResponseModel.getUuid());
            return new ResponseEntity<>(userResponseModel,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseModel> login(@RequestBody LoginRequestModel loginRequestModel) {
        UserResponseModel userResponseModel = userService.login(loginRequestModel);
        return userResponseModel != null ? new ResponseEntity<>(userResponseModel,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/searchUser", produces = "application/json")
    public ResponseEntity<List<SearchUserResponseModel>> searchUser(@Param("searchTerm") String searchTerm) {
        List<SearchUserResponseModel> searchUserResponseModels = esUserService.suggestUsers(searchTerm);
        return new ResponseEntity<>(searchUserResponseModels,HttpStatus.OK);
    }

    @PostMapping(value = "/getUsers", produces = "application/json")
    public ResponseEntity<GetUsersResponseModel> getUserByUuid(@RequestBody GetUsersRequestModel getUsersRequestModel) {
        GetUsersResponseModel getUsersResponseModel = esUserService.getUsers(getUsersRequestModel.getUserUuids());
        return new ResponseEntity<>(getUsersResponseModel,HttpStatus.OK);
    }


}
