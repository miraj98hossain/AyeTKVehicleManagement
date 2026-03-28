package com.aye.dtoLib.dto.response;

import lombok.Data;

@Data
public class CommonColumnResponse {
    private Integer createdBy;
    private String creationDate;
    private Integer lastUpdateBy;
    private Long lastUpdateLogin;
    private String lastUpdateDate;
}
