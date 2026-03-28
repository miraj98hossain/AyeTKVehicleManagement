package com.aye.dtoLib.dto.response;

import com.aye.enums.DataType;
import lombok.Data;

@Data
public class DataSetResponse {
    private Long id;
    private String dataSetName;
    private String tableName;
    private String columnName;
    private DataType columnNameDataType;
    private String columnId;
    private DataType columnIdDataType;
    private String wherClause;
    private String orderBy;
}
