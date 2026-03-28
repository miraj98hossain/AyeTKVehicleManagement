package com.aye.dtoLib.dto.response.user;

import com.aye.dtoLib.dto.response.MUserResponse;
import lombok.Data;

import java.util.Date;


@Data
public class EmpResDto {


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

    private MUserResponse muser;


    private Integer lookUpUserId;

    private Integer salesRepId;
    private String userName;

    private String lookupUserName;

    private Integer CreatedBy;

    private Integer LastUpdateBy;

    private Date creationDate;
    private Date LastUpdateDate;
    private Integer LastUpdateLogin;

    private Boolean notInApprovalProcess;

}
