package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestGroupLineResponse {
    private Integer reqGrpHdrId;
    private String requestType;
    private Boolean isActive;
}
