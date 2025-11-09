package com.aye.backendservice.service.implementations;

import com.aye.backendservice.mapper.StepTransTimelineMapper;
import com.aye.backendservice.repository.StepTransTimeLineRepository;
import com.aye.backendservice.service.StepTransTimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepTransTimeLineServiceImpl implements StepTransTimeLineService {
    @Autowired
    private StepTransTimeLineRepository StepTransTimeLineRepository;
    @Autowired
    private StepTransTimelineMapper transTimelineMapper;


}
