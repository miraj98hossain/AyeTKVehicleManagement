package com.aye.commonlib.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuResponse {
    private Integer id;
    private String menuName;
    private String moduleName;
    private String moduleCode;
    private String level;
    private Boolean isActive;
    private String pageType;
    private List<UserSubMenuResponse> subMenus;
}

