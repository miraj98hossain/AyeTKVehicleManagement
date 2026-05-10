package com.aye.dtoLib.dto.response;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 3:17 pm
 */

@Data
public class StepTransScaleDetailsDto {
    @NotNull(message = "Step Trans Id Can Not Be Null")
    private Long stepTransId;
    @NotNull(message = "Step Trans Line Id Can Not Be Null")
    private Long stepTransLineId;
    @NotNull(message = "Step Setup Details Id Can Not Be Null")
    private Long stepSetupDetailId;
    @NotBlank(message = "Scale Name Can Not Be Null")
    private String scaleName;
    @NotNull(message = "Weight Can Not Be Null")
    private Long weight;
    private Date createdAt;
    private Long createdBy;
    private Date updatedAt;
    private Long updatedBy;

}
