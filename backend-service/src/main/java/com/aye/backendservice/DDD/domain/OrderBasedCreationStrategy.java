package com.aye.backendservice.DDD.domain;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.om.BeforeTripV;
import com.aye.RestfulServer.service.BeforeTripVService;
import com.aye.backendservice.DDD.factory.StepTransDetailsFactory;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.entity.StepTransDetailsLines;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Component
public class OrderBasedCreationStrategy implements StepTransCreationStrategy {
    @Autowired
    private BeforeTripVService beforeTripVService;
    @Autowired
    private StepTransDetailsRepository repository;
    @Autowired
    private StepTransDetailsFactory factory;


    @Override
    public boolean supports(StepTransDetailsRequest req) {
        return req.getOrderNumber() != null;
    }

    @Override
    public List<StepTransDetails> create(StepTransDetailsRequest req, Muser user) {
        StepTransDetails stepTransDetails = factory.fromStepDetailsReq(req, user);
        List<StepTransDetails> existing = repository.findAllByOrderNumber(req.getOrderNumber());
        Set<Long> existingOrders = existing.stream().map(StepTransDetails::getOrderNumber).collect(Collectors.toSet());

        List<BeforeTripV> trips = beforeTripVService.findAllByOrderNumber(req.getOrderNumber());
        Map<Long, List<BeforeTripV>> grouped = trips.stream().collect(Collectors.groupingBy(BeforeTripV::getOrderNumber));

        List<StepTransDetails> result = new ArrayList<>();

        for (BeforeTripV trip : trips) {
            if (existingOrders.contains(trip.getOrderNumber())) continue;

            existingOrders.add(trip.getOrderNumber());

            StepTransDetails dtl = factory.fromBeforeTripV(trip, stepTransDetails.getStepTrans(), user);

            List<StepTransDetailsLines> lines = grouped.get(trip.getOrderNumber()).stream()
                    .map(t -> factory.lineFromBeforeTripV(t, dtl, user))
                    .toList();

            dtl.getStepTransDetailsLines().addAll(lines);
            result.add(dtl);
        }

        return repository.saveAll(result);
    }
}
