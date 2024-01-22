package com.messanger.userService.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.messanger.userService.models.entity.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateUserToGroupRequestModel {
    @NotBlank(message = "userUuid cannot be blank")
    @NotNull(message = "userUuid is mandatory")
    private String userUuid;

    private Role role;

    @NotNull(message = "action is mandatory")
    private Action action;
}
