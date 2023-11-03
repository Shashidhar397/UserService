package com.messanger.userService.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.messanger.userService.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {

    private String name;

    @JsonProperty("contact_number")
    @NonNull
    private String contactNumber;

    public User getUserEntity() {
        User user = new User(this.getName(), this.getContactNumber());
        return user;
    }

}
