package com.aye.dtoLib.dto.response;

import com.aye.enums.RptType;
import lombok.Data;

@Data
public class ExecutablesResponse {
    private Long id;
    private String name;
    private String fileName;
    private String moduleName;
    private String moduleCode;
    private String execPath;
    private String execType;
    private Boolean onReqPrint;
    private String printerName;
    private RptType outpuType;

}
