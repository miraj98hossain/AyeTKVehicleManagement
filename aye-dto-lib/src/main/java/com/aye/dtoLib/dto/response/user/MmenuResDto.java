package com.aye.dtoLib.dto.response.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MmenuResDto {

    private final Date creationDate = new Date();
    private int id;
    private String menuDesc;
    private String menuType;
    private String menuModule;
    private Integer menuSl;
    private Integer trnsAccessNumber;
    private Integer pageId;
    private Integer orgSetupId;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

}
