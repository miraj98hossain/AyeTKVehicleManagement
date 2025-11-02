package com.mhdev.webclient.controller;

import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.webclient.service.ApiResponseConversionService;
import com.mhdev.webclient.service.StepSetupService;
import com.mhdev.webclient.service.StepTransService;
import com.mhdev.webclient.service.VhCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;
    @Autowired
    private StepSetupService stepSetupService;
    @Autowired
    private VhCityService vhCityService;
    @Autowired
    private ApiResponseConversionService conversionService;


    @Autowired
    public StepTransController(StepTransService stepTransService) {
        this.stepTransService = stepTransService;
    }

    @GetMapping
    public String listStepTrans(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        conversionService.apiResponseConversion(stepSetupService.getAllStepsSetup(), model);
        conversionService.apiResponseConversion(vhCityService.getAllVehicleCity(), model);
        conversionService.apiResponseConversion(stepTransService.findAll(PageRequest.of(page, size)), model);
        model.addAttribute("stepTransReq", new StepTransRequest());// for creation form
        return "steps-trans-page";
    }

    // create a new StepTrans
    @PostMapping("/create")
    public String createNewStepTrans(@ModelAttribute("stepTransReq") StepTransRequest stepTransReq) {
        if (stepTransReq.getStepTransId() == null) {
            stepTransService.create(stepTransReq);
        }
        return "redirect:/step-trans";
    }

    @GetMapping("/update-lines")
    public String updateLines(@RequestParam(name = "stepTransLineId") Long stepTransLineId,
                              @RequestParam(name = "stepStatus") String stepStatus) {
        if (stepTransLineId != null) {
            StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
            stepTransLinesRequest.setStepTransLinesId(stepTransLineId);
            stepTransLinesRequest.setStepStatus(stepStatus);
            var res = stepTransService.updateLines(stepTransLinesRequest);
        }
        return "redirect:/step-trans";
    }
}
