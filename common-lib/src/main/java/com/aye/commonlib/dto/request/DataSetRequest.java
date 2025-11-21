package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DataSetRequest {

    private Long id;

    @NotBlank(message = "Dataset name is required")
    @Size(max = 100, message = "Dataset name must be up to 100 characters")
    private String dataSetName;

    @NotBlank(message = "Table name is required")
    @Size(max = 100, message = "Table name must be up to 100 characters")
    private String tableName;

    @NotBlank(message = "Column name is required")
    @Size(max = 100, message = "Column name must be up to 100 characters")
    private String columnName;

    @NotBlank(message = "Column data type is required")
    @Size(max = 50, message = "Column data type must be up to 50 characters")
    private String columnNameDataType;

    @NotBlank(message = "Column ID is required")
    @Size(max = 100, message = "Column ID must be up to 100 characters")
    private String columnId;

    @NotBlank(message = "Column ID data type is required")
    @Size(max = 50, message = "Column ID data type must be up to 50 characters")
    private String columnIdDataType;

    @Size(max = 300, message = "Where clause must be up to 300 characters")
    private String wherClause;

    @Size(max = 200, message = "Order by must be up to 200 characters")
    private String orderBy;
}


