package com.aye.dtoLib.dto.response.userOrg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestGroupHeaderResponse {
    private Integer id;
    private String groupName;
    private List<RequestGroupLineResponse> requestGroupLines;
}
