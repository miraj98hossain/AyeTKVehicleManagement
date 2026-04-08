package com.aye.dtoLib.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractListDto {
    Long orgId;

    Long invOrgId;

    Integer createdBy;

    Integer id;

    Long contNumber;
}