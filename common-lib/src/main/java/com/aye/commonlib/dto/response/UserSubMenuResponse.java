package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubMenuResponse {
    private Integer id;
    private String menuName;
    private String pageName;
    //userMenu details
    private Integer userMenuId;
    private String userMenuName;
    private PagesResponse page;
    private boolean isActive;
}
