package com.aye.commonlib.dto.response.userData;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuserBankResDto {

    private Long orgId;

    private String bankAccountName;

    private Integer bankId;

    private String refType;
}
