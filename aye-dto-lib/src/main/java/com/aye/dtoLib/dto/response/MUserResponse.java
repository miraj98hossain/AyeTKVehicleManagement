package com.aye.dtoLib.dto.response;

import com.aye.enums.UserType;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class MUserResponse {
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String lastName;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;
    private String autoNumber;
    private UserType userType;
    private boolean isPassChange;
    private boolean chkDeviceId;
    private Date lastLogin;
    private List<MRoleResponse> roles;
}
