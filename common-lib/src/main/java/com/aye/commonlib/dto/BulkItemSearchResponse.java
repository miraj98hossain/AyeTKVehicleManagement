package com.aye.commonlib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkItemSearchResponse {
    public Long orgId;
    public Long invOrgId;
    public Long invItemId;
    public String itemCode;
    public String itemMask;
}