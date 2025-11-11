package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagesResponse {
    private String pageName;
    private String pageUrl;
    private Boolean status;
    private Integer pageGroup;
    private String psysicalName;
    private String pageType;
    private Integer appPageId;
    private Integer appId;
    private Integer shortOrder;
    private String function;
}
