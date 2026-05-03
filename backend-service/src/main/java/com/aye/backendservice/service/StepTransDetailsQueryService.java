package com.aye.backendservice.service;

/**
 * @author: Miraj
 * @date: 12/01/2026
 * @time: 09:52
 */


import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.mapper.StepTransDetailsLinesMapper;
import com.aye.mapper.StepTransDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StepTransDetailsQueryService {
    @Autowired
    private StepTransDetailsRepository detailsRepo;
    @Autowired
    private StepTransDetailsLinesRepository linesRepo;
    @Autowired
    private StepTransDetailsMapper detailsMapper;
    @Autowired
    private StepTransDetailsLinesMapper linesMapper;


}

