package com.aye.commonlib.dto.response;

import lombok.Data;

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
    private String userType;
    private Boolean isPassChange;
    private Boolean chkDeviceId;
    private List<MRoleResponse> roles;
}
