package com.aye.backendservice.service.domain;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:47
 */


import com.aye.RestfulServer.service.BeforeTripWDsVService;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.backendservice.service.factory.StepTransDetailsFactory;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.entitylib.entity.BeforeTripWDsV;
import com.aye.entitylib.entity.Muser;
import com.aye.entitylib.entity.StepTransDetails;
import com.aye.entitylib.entity.StepTransDetailsLines;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleBasedDetailsCreationStrategy implements StepTransDetailsCreationStrategy {

    private final BeforeTripWDsVService beforeTripWDsVService;
    private final StepTransDetailsRepository repository;
    private final StepTransDetailsFactory factory;

    public ScheduleBasedDetailsCreationStrategy(BeforeTripWDsVService beforeTripWDsVService,
                                                StepTransDetailsRepository repository,
                                                StepTransDetailsFactory factory) {
        this.beforeTripWDsVService = beforeTripWDsVService;
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public boolean supports(StepTransDetailsRequest req) {
        return req.getScheduleNo() != null && !req.getScheduleNo().isEmpty();
    }

    @Override
    public List<StepTransDetails> create(StepTransDetailsRequest req, Muser user) {
        StepTransDetails stepTransDetails = factory.fromStepDetailsReq(req, user);
        List<StepTransDetails> existing = repository.findAllByScheduleNo(req.getScheduleNo());
        Set<Long> existingOrders = existing.stream().map(StepTransDetails::getOrderNumber).collect(Collectors.toSet());

        List<BeforeTripWDsV> trips = beforeTripWDsVService.findAllByScheduleNumber(req.getScheduleNo());
        Map<Long, List<BeforeTripWDsV>> grouped = trips.stream().collect(Collectors.groupingBy(BeforeTripWDsV::getOrderNumber));

        List<StepTransDetails> result = new ArrayList<>();

        for (BeforeTripWDsV trip : trips) {
            if (existingOrders.contains(trip.getOrderNumber())) continue;

            existingOrders.add(trip.getOrderNumber());

            StepTransDetails dtl = factory.fromBeforeTripWDsV(trip, stepTransDetails.getStepTrans(), user);

            List<StepTransDetailsLines> lines = grouped.get(trip.getOrderNumber()).stream()
                    .map(t -> factory.lineFromBeforeTripWDsV(t, dtl, user))
                    .toList();

            dtl.getStepTransDetailsLines().addAll(lines);
            result.add(dtl);
        }

        return repository.saveAll(result);
    }
}

