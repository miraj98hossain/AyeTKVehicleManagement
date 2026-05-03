package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.StepWiseTransCountVResponse;
import com.aye.entitylib.entity.vehicleproject.StepWiseTransCountV;
import com.aye.mapper.StepWiseTransCountVMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/04/2026
 * @time: 4:30 pm
 */
@Service
@RequiredArgsConstructor
public class StepWiseTransCountVViewService {
    private final StepWiseTransCountVService stepWiseTransCountVService;

    private final StepWiseTransCountVMapper stepWiseTransCountVMapper;

    ApiRequestResponse getCountByDetailId(List<Long> detailId) {
        List<StepWiseTransCountV> list = stepWiseTransCountVService.getCountByDetailId(detailId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepWiseTransCount",
                StepWiseTransCountVResponse.class.getName(),
                this.stepWiseTransCountVMapper.toResponseDto(list)
        );
    }
}
