package com.aye.commonlib.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MUserRequest {

    private Integer id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3–50 characters")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be 6–100 characters")
    private String password;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;

    @Size(max = 20, message = "Auto Number must not exceed 20 characters")
    private String autoNumber;

    @NotBlank(message = "User type is required")
    @Size(max = 30, message = "User type must not exceed 30 characters")
    private String userType;
    @NotNull(message = "Role id is required")
    private Integer roleId;
    private boolean isPassChange;
    private boolean chkDeviceId;
}

