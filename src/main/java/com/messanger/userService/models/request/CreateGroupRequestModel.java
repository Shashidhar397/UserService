package com.messanger.userService.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shashidhar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupRequestModel {
    private String name;
    private String description;
    private String userUuid;
}
