package com.aye.commonlib.dto.response;

import lombok.Data;


@Data
public class ExecutableParameterResponse {

    private String paraMeterName;
    private String dataType;
    private String parameterFieldType;
    private String parameterToken;
//    @ManyToOne
//    @JoinColumn(name = "DATA_SET_ID")
//    private DataSet dataSet;

    private String dataSetName;
    private String displayField;
    private String isReadOnly;
    private ExecutablesResponse executables;
    private String javaClass;
    private String paraMetervalue;
    private String paraMeterId;
    private Boolean isRequired;
    private String defaultValueType;
    private String defaultValue;
    private String defaultModal;
    private String modalBtn;
    private String paramButtnSize = "col-sm-7";

//    private List<DataSetJson> dataSetJsonList;


}
