package com.aye.commonlib.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuserEmpVResDto {


    private Long userId;

    private String userName;

    private Long empId;

    private String firstName;
    private String lastName;

    private Long lookupUserId;

    private Long salesrepId;

    private Long nUserId;

    private Long orgId;

    private Long notInApprovalProcess;

}