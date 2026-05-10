package com.aye.dtoLib.dto.request;

import com.aye.enums.AccessCodeLevel;
import com.aye.enums.AccessCodeType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserCodeAccessRequest {

    private Long id;
    @NotNull(message = "User cannot be null")
    private Integer userId;
    @NotNull(message = "Access code type is required")
    private AccessCodeType accessCodeType;
    @NotNull(message = "Access code level is required")
    private AccessCodeLevel accessCodeLevel;

    private Long orgId;
    private Long itemCatCombId;
    private String itemCatComb;
    private Long invInfoId;
    private String invInfoCode;
    private Long scaleSetupId;
    private String scaleSetupName;
    private Long userTransTypeId;
    private String userTransTypeName;
    private Date startDate;

    private Date endDate;

}
