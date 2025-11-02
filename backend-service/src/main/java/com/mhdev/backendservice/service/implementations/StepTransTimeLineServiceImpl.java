package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.mapper.StepTransTimelineMapper;
import com.mhdev.backendservice.repository.StepTransTimeLineRepository;
import com.mhdev.backendservice.service.StepTransTimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepTransTimeLineServiceImpl implements StepTransTimeLineService {
    @Autowired
    private StepTransTimeLineRepository StepTransTimeLineRepository;
    @Autowired
    private StepTransTimelineMapper transTimelineMapper;


}
