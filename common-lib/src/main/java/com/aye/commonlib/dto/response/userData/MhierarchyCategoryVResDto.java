package com.aye.commonlib.dto.response.userData;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Munna on 5/22/2023.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MhierarchyCategoryVResDto {

    private Integer lineId;


    private Integer orgSetId;


    private Integer headerId;


    private String code;


    private String description;


    private Date startDate;


    private Date endDate;


    private String status;


    private String categoryType;


    private Long orgId;


    private String categoryValue;


    private Integer categoryValueId;


    private Date lineStartDate;


    private Date lineEndDate;


    private String lineStatus;


}
