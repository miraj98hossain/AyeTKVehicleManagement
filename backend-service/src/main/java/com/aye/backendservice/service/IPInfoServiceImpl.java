package com.aye.backendservice.service;

import com.aye.backendservice.mapper.ScaleSetupMapper;
import com.aye.backendservice.repository.IPInfoRepo;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.ScaleSetupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
@Service
public class IPInfoServiceImpl implements IPInfoService {
    @Autowired
    private IPInfoRepo ipInfoRepo;
    @Autowired
    private ScaleSetupMapper scaleSetupMapper;

    @Override
    public ApiRequestResponse findAllIPInfo() {
        var list = ipInfoRepo.findAll().stream().map(scaleSetupMapper::toIPInfoResponse).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "ipList",
                ScaleSetupResponse.class.getName(),
                list
        );
    }
}
