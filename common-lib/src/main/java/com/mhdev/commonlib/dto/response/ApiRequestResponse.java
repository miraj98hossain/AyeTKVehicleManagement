package com.mhdev.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@ToString
@Builder
public class ApiRequestResponse {
    private Long id;
    private String httpStatus;
    private String message;
    private List<ApiRequestResponseDetail> apiRequestResponseDetails = new ArrayList<>();

    public ApiRequestResponse() {
        this.id = new Random().nextLong();
    }

}
