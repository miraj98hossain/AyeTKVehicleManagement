package com.aye.commonlib.dto.request;

import lombok.Data;

/**
 * @author: Miraj
 * @date: 23/12/2025
 * @time: 19:48
 * @project: AyeTKVehicleManagement
 */
@Data
public class UserActionAccessRequest {
    private Long id;
    private Long userId;
    private Long orgHierarchyId;
    private Long inventoryInformationId;
    private Long pagesId;
    private Long userMenuId;
    private String buttonAccess;
    private String entryFrom;
}
