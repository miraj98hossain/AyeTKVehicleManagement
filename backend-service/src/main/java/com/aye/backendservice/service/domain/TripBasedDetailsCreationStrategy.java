package com.aye.backendservice.service.domain;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:47
 */


import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.om.XxtkgTripDelvDtlV;
import com.aye.RestfulServer.service.XxtkgTripDelvDtlVService;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.backendservice.service.factory.StepTransDetailsFactory;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional()
public class TripBasedDetailsCreationStrategy implements StepTransDetailsCreationStrategy {
    @Autowired
    private XxtkgTripDelvDtlVService xxtkgTripDelvDtlVService;
    @Autowired
    private StepTransDetailsRepository repository;
    @Autowired
    private StepTransDetailsFactory factory;


    @Override
    public boolean supports(StepTransDetailsRequest req) {
        return req.getChallanNumber() != null && !req.getChallanNumber().isEmpty();
    }

    @Override
    public List<StepTransDetails> create(StepTransDetailsRequest req, Muser user) {
        StepTransDetails stepTransDetails = factory.fromStepDetailsReq(req, user);
        List<XxtkgTripDelvDtlV> trips = xxtkgTripDelvDtlVService.findByChallanNumber(req.getChallanNumber());
        Set<Long> addedOrderNumbers = new HashSet<>();
        List<StepTransDetails> result = new ArrayList<>();
        for (XxtkgTripDelvDtlV trip : trips) {
            if (addedOrderNumbers.contains(trip.getOrderNumber())) continue;
            addedOrderNumbers.add(trip.getOrderNumber());
            StepTransDetails dtl = factory.fromTripDtlView(trip, stepTransDetails.getStepTrans(), user);
            result.add(dtl);
        }
        repository.deleteAllByStepTrans_StepTransId(stepTransDetails.getStepTrans().getStepTransId());
        return repository.saveAll(result);
    }
}

