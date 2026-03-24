package com.aye.commonlib.dto.response.userOrg;

import com.aye.enums.AppModuleCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppModuleResponse {
    private Long moduleId;
    private String moduleName;
    private AppModuleCode moduleCode;
}
