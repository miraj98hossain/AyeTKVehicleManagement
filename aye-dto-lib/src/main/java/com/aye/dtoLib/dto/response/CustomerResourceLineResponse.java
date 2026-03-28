package com.aye.dtoLib.dto.response;

import com.aye.enums.RegularData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResourceLineResponse {
    private Integer lineId;
    private Integer empResourceId;
    private Integer orgId;
    private Integer custHeaderId;
    private Integer custAccountId;
    private String customerName;
    private String accountNumber;
    private Date start_date;
    private String end_date;
    private RegularData.ResourceStatus resourceStatus;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date creationDate;
    private Date lastUpdateDate;
    private Date lastUpdateLogin;
}
