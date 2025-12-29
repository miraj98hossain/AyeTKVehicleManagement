package com.aye.commonlib.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * @author: Miraj
 * @date: 23/12/2025
 * @time: 19:53
 * @project: AyeTKVehicleManagement
 */
@Data
public class UserActionAccessResponse {

    private Long id;
    private Long userId;
    private String userName;
    private Long orgId;
    private String orgCode;
    private Long inventoryId;
    private String inventoryCode;
    private Long pagesId;
    private String pagesName;
    private Long menuId;
    private String menuName;
    private String buttonAccess;
    private String entryFrom;
    private Integer createdBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdateDate;
}
