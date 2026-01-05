package com.aye.commonlib.dto.response;

import lombok.Data;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:08
 */
@Data
public class OrderedCustomerVResponse {
    private Long id;
    private Long orgId;
    private String partyName;
    private String accountNumber;
    private Long custAccountId;
}
