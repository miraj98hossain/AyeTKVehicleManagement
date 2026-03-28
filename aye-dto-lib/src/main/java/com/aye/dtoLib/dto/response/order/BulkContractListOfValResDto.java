package com.aye.dtoLib.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkContractListOfValResDto {


    private Integer id;


    private Long orgId;


    private String typeName;


    private String typeVal;


    private String trnsType;


    private Integer trnsTypeId;


    private String typeValName;

}