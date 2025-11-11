package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSubInvAccessResponse {
    private Integer id;
    private String name;
    private Integer userTransactionTypeId;
    private String subInv;
}
