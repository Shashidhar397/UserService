package com.messanger.userService.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserResponseModel {

    @JsonProperty("user_name")
    private String userName;
    private String email;
    @JsonProperty("user_id")
    private String userId;
}
