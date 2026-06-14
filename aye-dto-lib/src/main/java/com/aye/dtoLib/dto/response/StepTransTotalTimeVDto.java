package com.aye.dtoLib.dto.response;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class StepTransTotalTimeVDto {
    private Long id;
    private Long stepTransId;
    private String vehicleNumber;
    private String description;
    private BigDecimal totalTime;
}