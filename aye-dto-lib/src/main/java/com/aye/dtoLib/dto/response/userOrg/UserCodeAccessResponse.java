package com.aye.dtoLib.dto.response.userOrg;

import com.aye.enums.AccessCodeLevel;
import com.aye.enums.AccessCodeType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserCodeAccessResponse {
    private Long id;
    private Integer userId;
    private String userName;
    private AccessCodeType accessCodeType;
    private AccessCodeLevel accessCodeLevel;
    private Long orgHierarchyId;
    private String orgHierarchyCode;
    private Long invInfoId;
    private String invInfoCode;
    private String invInfoName;
    private Long itemCatCombId;
    private String itemCatComb;
    private Long scaleSetupId;
    private String scaleSetupName;
    private String scaleSetupIp;
    private Long userTransTypeId;
    private Long transId;
    private String transName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
