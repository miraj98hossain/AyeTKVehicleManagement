package com.aye.commonlib.dto.request;

import lombok.Data;


@Data
public class ExecutableParameterRequest {
    private String paraMeterName;
    private String dataType;
    private Long dataSetId;
    private Long executableId;
    private String parameterFieldType;
    private String parameterToken;
    private String javaClass;
    private Boolean isRequired;
    private String defaultValueType;
    private String defaultValue;
    private String defaultModal;
}
