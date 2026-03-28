package com.aye.dtoLib.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuserFormResDto {

    private Integer id;
    private String description;

    private String psysicalName;

    private String pageType;
    private Integer appPageId;

    private Integer appId;

    private Integer shortOrder;

    private String function;


    private Integer createdBy;
    private Integer lastUpdateBy;
    private LocalDate creationDate;
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

}
