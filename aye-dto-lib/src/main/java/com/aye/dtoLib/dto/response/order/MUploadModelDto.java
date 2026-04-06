package com.aye.dtoLib.dto.response.order;

import com.aye.enums.MuploadTrnsSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MUploadModelDto {


    private Integer id;


    private String name;


    private String type;

    private byte[] imgFile;


    private Integer createdBy;


    private Date creationDate;


    private Integer lastUpdateBy;


    private Date lastUpdateDate;


    private String imgUrl;


    private OnlineCollectionDto onlineCollectionResDto;


    private BulkContractPaymentDto bulkContractPayment;

    private MuploadTrnsSource trnsSource;


}