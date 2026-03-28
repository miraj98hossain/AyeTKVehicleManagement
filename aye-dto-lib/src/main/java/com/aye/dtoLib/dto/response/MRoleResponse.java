package com.aye.dtoLib.dto.response;

import com.aye.enums.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MRoleResponse {
    private int id;
    private String role;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private String creationDate;
    private String lastUpdateDate;
    private Integer lastUpdateLogin;
    private RoleTypes roleTypes;
}
