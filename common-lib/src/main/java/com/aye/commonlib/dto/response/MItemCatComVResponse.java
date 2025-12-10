package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class MItemCatComVResponse {
    private Long id;
    private Long orgId;
    private Long invOrgId;
    private String category;
    private String family;
    private String itemClass;
    private String combination;
}
