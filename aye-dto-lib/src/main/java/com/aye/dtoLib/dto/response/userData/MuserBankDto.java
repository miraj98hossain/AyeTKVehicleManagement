package com.aye.dtoLib.dto.response.userData;


import com.aye.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuserBankDto {

    private Long orgId;

    private String bankAccountName;

    private Integer bankId;

    private PaymentType refType;
}
