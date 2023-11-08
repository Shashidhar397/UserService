package com.messanger.userService.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.messanger.userService.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {

    private String userId;
    private String userName;
    private String email;

    public static UserResponseModel getUserResponseModel(User user) {
        return new UserResponseModel(user.getId(), user.getName(), user.getEmail());
    }
}
