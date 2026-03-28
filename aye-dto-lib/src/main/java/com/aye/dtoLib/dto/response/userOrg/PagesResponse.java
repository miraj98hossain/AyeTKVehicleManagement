package com.aye.dtoLib.dto.response.userOrg;

import com.aye.enums.PageUrl;
import com.aye.enums.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagesResponse {
    private Long id;
    private String pageName;
    private PageUrl pageUrl;
    private RoleTypes pageType;
    private String pageUrlDisplayName;
    private Boolean status;
    private Integer pageGroup;
    private String physicalName;
    private Integer appPageId;
    private Integer appId;
    private Integer shortOrder;
    private String function;
}
