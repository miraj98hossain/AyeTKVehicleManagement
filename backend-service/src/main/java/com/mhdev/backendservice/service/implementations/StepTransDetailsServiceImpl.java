package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.repository.StepTransDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StepTransDetailsServiceImpl {
    @Autowired
    private StepTransDetailsRepository stepTransDetailsRepository;
}
