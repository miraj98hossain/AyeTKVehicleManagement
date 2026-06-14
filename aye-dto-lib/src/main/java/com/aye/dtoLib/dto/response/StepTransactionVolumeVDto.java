package com.aye.dtoLib.dto.response;

import com.aye.enums.StepTransStatus;
import lombok.Data;

/**
 * @author: Miraj
 * @date: 07/06/2026
 * @time: 11:54 am
 */
@Data
public class StepTransactionVolumeVDto {
    Long id;
    Long stepSetupId;
    String description;
    StepTransStatus stepTransStatus;
    Long vehicleCount;
}
