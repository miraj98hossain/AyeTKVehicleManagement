package com.aye.vhmwebclient.service;


import com.aye.dtoLib.dto.request.StepTransDetailsLinesRequest;
import com.aye.dtoLib.dto.request.StepTransDetailsRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.vhmwebclient.feignclient.StepTransDetailsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 29/12/2025
 * @time: 13:16
 * @project: AyeTKVehicleManagement
 */
@Service
public class StepTransDetailsService {
    @Autowired
    private StepTransDetailsFeignClient stepTransDetailsFeignClient;

    public ApiRequestResponse create(
            String userName,
            StepTransDetailsRequest stepTransDetailsRequest
    ) {
        return this.stepTransDetailsFeignClient.create(userName, stepTransDetailsRequest).getBody();
    }

    public ApiRequestResponse findAllByStepTransId(Long stepTransId) {
        return this.stepTransDetailsFeignClient.findAllByStepTransId(stepTransId).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return this.stepTransDetailsFeignClient.findById(id).getBody();
    }


    public ApiRequestResponse deleteById(Long id) {
        return this.stepTransDetailsFeignClient.deleteById(id).getBody();
    }

    //***Line Section ***********
    public ApiRequestResponse saveStDtlLine(StepTransDetailsLinesRequest stepTrnsDtlLnsReq,
                                            String userName) {
        return this.stepTransDetailsFeignClient.saveStDtlLine(stepTrnsDtlLnsReq, userName).getBody();
    }

    public ApiRequestResponse findAllLinesByStepTransIdAndUserItemAccess(Long stepTransId, String userName) {
        var res = this.stepTransDetailsFeignClient.findAllLinesByStepTransIdAndUserItemAccess(stepTransId, userName).getBody();
        return res;
    }

    public ApiRequestResponse findStDtlLineById(Long stepTransDtlLnId) {
        return this.stepTransDetailsFeignClient.findStDtlLineById(stepTransDtlLnId).getBody();
    }

    public ApiRequestResponse findAllByStTrnDtlId(Long stepTransDtlId) {
        return this.stepTransDetailsFeignClient.findAllByStTrnDtlId(stepTransDtlId).getBody();
    }

    public ApiRequestResponse deleteLineById(Long id) {
        return this.stepTransDetailsFeignClient.deleteLineById(id).getBody();
    }

    public ApiRequestResponse loadingStatusUpdate(Long stepTransLineId, Long detailLineId, String stepStatus, String userName) {

        return this.stepTransDetailsFeignClient.loadingStatusUpdate(stepTransLineId, detailLineId, stepStatus, userName).getBody();

    }
}
