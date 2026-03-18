package com.aye.commonlib.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkItemSearchResDto {
    public Long orgId;
    public Long invOrgId;
    public Long invItemId;
    public String itemCode;
    public String itemMask;
}