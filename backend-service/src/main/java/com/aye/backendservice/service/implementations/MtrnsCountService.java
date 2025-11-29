package com.aye.backendservice.service.implementations;


import com.aye.backendservice.entity.MtrnsCount;
import com.aye.backendservice.repository.MtrnsCountRepo;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by toufiq on 5/28/2021.
 */
@Service
public class MtrnsCountService {
    @Autowired
    private MtrnsCountRepo mtrnsCountRepo;

    public ApiRequestResponse findAll() {

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully retrieve all mtrnsCount",
                ApiRequestResponseDetail.ObjectType.A, "recCount",
                MtrnsCount.class.getName(), this.mtrnsCountRepo.findAll()
        );
    }
}
