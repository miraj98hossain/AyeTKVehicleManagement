package com.aye.commonlib.dto.response.userData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuserDataCommonResDto {

    private Integer id;


    private String autoNumber;

    private Long orgId;


    private Integer transactionTypeId;

    private String transactionTypeName;


    private Integer invOrgId;


    public MuserDataCommonResDto(String AutoNumber, Long OrgId, Integer TransactionTypeId,
                                 String TransactionTypeName, Integer InvOrgId) {
        this.autoNumber = AutoNumber;
        this.orgId = OrgId;
        this.transactionTypeId = TransactionTypeId;
        this.transactionTypeName = TransactionTypeName;
        this.invOrgId = InvOrgId;
    }
}
