package com.aye.dtoLib.dto.response;

import com.aye.enums.RegularData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResourceHeaderResponse {
    private Integer id;
    private Integer custAccountId;
    private String customerName;
    private String accountNumber;
    private Long orgId;
    private Date start_date;
    private Date end_date;
    private RegularData.ResourceStatus resourceStatus;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private String creationDate;
    private String lastUpdateDate;
    private String lastUpdateLogin;
    private List<CustomerResourceLineResponse> customerResourceLines;
}
