package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class UserAccessResponse {
    private Integer id;
    private String accessNumber;
    private Integer userId;
    private String userName;
    private UserAccessTempltResponse userAccessTemplt;
    private String start_date;
    private String end_date;
}
