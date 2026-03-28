package com.aye.dtoLib.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpReqDto {


    private Long id;

    private Integer orgId;

    private Integer empId;

    private String emp_first_name;
    private String emp_last_name;

    private String gender;

    private String desig;

    private String department;

    private Date start_date;
    private Date end_date;

    private Integer muser;


    private Integer lookUpUserId;

    private Integer salesRepId;
    private String userName;

    private String lookupUserName;

    private Boolean notInApprovalProcess;

}
