package com.aye.dtoLib.dto.response.userData;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MitemCatVResDto {


    private Long id;


    private Long orgId;


    private Long invOrgId;


    private String itemCategories;


    private String itemFamily;


    private String itemClass;


    private Integer inventoryItemId;


    private String itemCode;


    private String itemMask;


    private String className;

}
