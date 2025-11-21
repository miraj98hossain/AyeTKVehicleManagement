package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class DataSetResponse {
    private Long id;
    private String dataSetName;
    private String tableName;
    private String columnName;
    private String columnNameDataType;
    private String columnId;
    private String columnIdDataType;
    private String wherClause;
    private String orderBy;
}
