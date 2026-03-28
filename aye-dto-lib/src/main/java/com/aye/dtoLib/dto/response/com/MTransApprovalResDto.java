package com.aye.dtoLib.dto.response.com;

import lombok.Data;

import java.util.Date;

/**
 * @author: Miraj
 * @date: 15/01/2026
 * @time: 12:03
 */
@Data
public class MTransApprovalResDto {
    private Long seqId;
    private Long transId;
    private Long orgId;
    private Long organizationId;
    private String transType;
    private Date transDate;
    private Long fromUserId;
    private String fromUser;
    private Long toUserId;
    private String toUser;
    private String approvalType;
    private String approveFrom;
    private String reason;
    private Long priorityUserGroup;

    private Long createdBy;
    private Date creationDate;
    private Long lastUpdatedBy;
    private Date lastUpdateDate;
}
