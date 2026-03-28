package com.aye.dtoLib.dto.response.userData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpHrHierchyResDto {


    private Long id;

    private String hierarchyValue;

    private Integer hierarchyId;


    private String hierarchyName;

    private Integer parentId;

    private String parentName;

    private String status;

    private Integer orgSetId;

    private Date start_date = new Date();


    private Date end_date;

    private Integer CreatedBy;

    private Integer LastUpdateBy;

    private Date creationDate = new Date();

    private Date LastUpdateDate;

    private Integer LastUpdateLogin;

    private String setStatus;

    private Long geoSetId;

    private String geoSetName;

}
