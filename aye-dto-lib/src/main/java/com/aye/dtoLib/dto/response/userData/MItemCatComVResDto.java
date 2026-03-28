package com.aye.dtoLib.dto.response.userData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MItemCatComVResDto {
    private Long id;
    private Long orgId;
    private Long invOrgId;
    private String category;
    private String family;
    private String itemClass;
}