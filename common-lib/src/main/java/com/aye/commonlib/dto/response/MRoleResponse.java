package com.aye.commonlib.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class MRoleResponse {
    private int id;
    private String role;
    private Set<MUserResponse> musers;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private String creationDate;
    private String lastUpdateDate;
    private Integer lastUpdateLogin;
    private String roleTypes;
}
