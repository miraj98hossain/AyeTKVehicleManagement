package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessTempltResponse {
    private Integer id;
    private String tempNumber;
    private String tempDesc;
    private List<UserAccessTemltDtlResponse> userAccessTemltDtls;
}
