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
    private Long id;
    private String name;
    private Long userTransactionTypeId;
    private String subInv;
}
