package com.aye.backendservice.service.domain.stepTransDetails;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:47
 */


import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.postgresEntity.BeforeTripSchedule;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.backendservice.service.factory.StepTransDetailsFactory;
import com.aye.backendservice.service.orgManagement.BeforeTripWDsVService;
import com.aye.dto.request.StepTransDetailsRequest;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTransDetails;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
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

    @EnableLogging
    @Override
    public List<StepTransDetails> create(StepTransDetailsRequest req, Muser user) {
        StepTransDetails stepTransDetails = factory.fromStepDetailsReq(req, user);
        List<StepTransDetails> existing = repository.findAllByScheduleNoAndStepTrans_StepTransId(req.getScheduleNo(), req.getStepTransId());
        Set<Long> existingOrders = existing.stream().map(StepTransDetails::getOrderNumber).collect(Collectors.toSet());

        List<BeforeTripSchedule> trips = beforeTripWDsVService.findAllByScheduleNumber(req.getScheduleNo());
        Map<Long, List<BeforeTripSchedule>> grouped = trips.stream().collect(Collectors.groupingBy(BeforeTripSchedule::getOrderNumber));

        List<StepTransDetails> result = new ArrayList<>();

        for (BeforeTripSchedule trip : trips) {
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

