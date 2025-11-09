package com.aye.backendservice.service.implementations;

import com.aye.backendservice.repository.StepTransDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StepTransDetailsServiceImpl {
    @Autowired
    private StepTransDetailsRepository stepTransDetailsRepository;
}
