package com.aye.commonlib.dto.request;

import lombok.Data;

@Data
public class UserSearchRequest {
    private String userName;
    private String firstName;
    private String lastName;
}
