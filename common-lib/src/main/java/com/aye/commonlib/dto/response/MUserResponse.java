package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class MUserResponse {
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String lastName;
}
