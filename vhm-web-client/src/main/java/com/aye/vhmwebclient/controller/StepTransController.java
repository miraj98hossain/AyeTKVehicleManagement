package com.aye.vhmwebclient.controller;

import com.aye.vhmwebclient.service.ApiResponseConversionService;
import com.aye.vhmwebclient.service.StepSetupService;
import com.aye.vhmwebclient.service.StepTransService;
import com.aye.vhmwebclient.service.VhCityService;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
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
        if (stepTransReq.getStepTransNo() == null) {
            stepTransService.create(stepTransReq);
        }
        return "redirect:/step-trans";
    }

    @GetMapping("/update-lines")
    public String updateLines(@RequestParam(name = "stepTransLinesNo") String stepTransLinesNo,
                              @RequestParam(name = "stepStatus") String stepStatus) {
        if (stepTransLinesNo != null) {
            StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
            stepTransLinesRequest.setStepTransLinesNo(stepTransLinesNo);
            stepTransLinesRequest.setStepStatus(stepStatus);
            var res = stepTransService.updateLines(stepTransLinesRequest);
        }
        return "redirect:/step-trans";
    }
}
