package com.aye.dtoLib.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoResDto {
    Integer orgId;

    Integer invItemId;

    String itemCode;

    String itemMask;
}