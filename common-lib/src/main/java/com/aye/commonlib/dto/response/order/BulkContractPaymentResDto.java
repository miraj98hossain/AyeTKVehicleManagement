package com.aye.commonlib.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkContractPaymentResDto {


    private Integer paymentId;
    private BulkContractResDto bulkContractResDto;


    private Long contNumber;


    private String payType;


    private String refNumber;


    private String trnsNumber;


    private Long orgId;


    private Long targetOrgId;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trnsDate;


    private String depositBank;


    private Integer depositBankId;


    private contPaymentStatus status;


    private String customerName;


    private String customerAddress;


    private Integer partyId;


    private String accountNumber;


    private Integer custAccId;


    private BigDecimal amount;


    private List<MUploadModelResDto> uploadModels = new ArrayList<>();


    private Integer createdBy;


    private Date CreationDate;


    private Integer LastUpdateBy;

    private Date LastUpdateDate;

    @Getter
    public enum contPaymentStatus {
        N("New"), P("Posted"), C("Cancel");
        private final String displayName;

        contPaymentStatus(String displayName) {
            this.displayName = displayName;
        }

    }

}
