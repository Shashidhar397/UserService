package com.messanger.userService.models.request;

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
public class CreateUserRequestModel {

    private String userName;
    private String email;
    private String password;
    @JsonProperty("confirm_password")
    private String confirmPassword;

    public User getUserEntity() {
        User user = new User(this.getUserName(), this.getEmail(), this.getPassword());
        return user;
    }

}
