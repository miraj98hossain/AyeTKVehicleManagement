package com.aye.dtoLib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class StrStepWiseTotalTimeVDto {

    private Long id;
    private Long stepTransId;
    private Long stepTransLinesId;
    private String vehicleNumber;
    private String description;
    private String stepName;
    private LocalDateTime started;
    private LocalDateTime ended;
    private BigDecimal totalTime;
}