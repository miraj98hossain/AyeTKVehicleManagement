package com.aye.commonlib.dto.response.com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MWfItemTypeResDto {
    private Long id;
    private String lookupType;
    private String lookupCode;

    private String displayName;

    private String itemType;

    private String wfType;
}