package com.aye.dtoLib.dto.response;

import com.aye.enums.DefaultModal;
import com.aye.enums.ParameterFieldType;
import com.aye.enums.defaultParameterValueType;
import lombok.Data;


@Data
public class ExecutableParameterResponse {

    private String paraMeterName;
    private String dataType;

    private String parameterToken;
    private ParameterFieldType parameterFieldType;
    private defaultParameterValueType defaultValueType;
    private String dataSetName;
    private String displayField;
    private String isReadOnly;
    private Long executablesId;
    private String executablesName;
    private String javaClass;
    private String paraMetervalue;
    private String paraMeterId;
    private Boolean isRequired;
    private DefaultModal defaultModal;
    private String defaultValue;
    private String modalBtn;
    private String paramButtnSize = "col-sm-7";

//    private List<DataSetJson> dataSetJsonList;


}
