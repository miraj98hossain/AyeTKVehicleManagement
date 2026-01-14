package com.aye.commonlib.dto.response;

import lombok.Data;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:19
 */
@Data
public class ScaleSetupResponse {
    private Long id;
    private String name;
    private String ipAddress;
    private String orgCode;
    private String orgId;
}
