package com.mhdev.webclient.controller;

import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import com.mhdev.webclient.service.ApiResponseConversionService;
import com.mhdev.webclient.service.StepSetupService;
import com.mhdev.webclient.service.StepTransService;
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
        conversionService.apiResponseConversion(stepTransService.findAll(PageRequest.of(page, size)), model);
        model.addAttribute("stepTransResponse", new StepTransResponse());            // for creation form
        model.addAttribute("stepTransLinesResponse", new StepTransLinesResponse());    // for update-lines form (can be reused per row)

        return "steps-trans-page";
    }

    // create a new StepTrans
    @PostMapping("/create")
    public String createNewStepTrans(@ModelAttribute("stepTransResponse") StepTransResponse stepTrans) {
        if (stepTrans.getStepTransId() == null) {
            StepTransRequest stepTransRequest = new StepTransRequest();
            stepTransRequest.setStepSetupId(stepTrans.getStepSetupId());
            stepTransRequest.setVehicleNumber(stepTrans.getVehicleNumber());
            stepTransRequest.setDriverPhoneNo(stepTrans.getDriverPhoneNo());
            stepTransRequest.setPartyName(stepTrans.getPartyName());
            stepTransRequest.setItem(stepTrans.getItem());
            stepTransRequest.setQuantity(stepTrans.getQuantity());
            stepTransRequest.setTransportName(stepTrans.getTransportName());
            stepTransService.create(stepTransRequest);
        }
        return "redirect:/step-trans";
    }

    // update step trans lines
//    @PostMapping("/update-lines")
//    public String updateLines(@ModelAttribute("linesRequest") StepTransLinesResponse linesRequest) {
//        if (linesRequest.getStepTransLinesId() != null) {
//            StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
//            stepTransLinesRequest.setStepTransLinesId(linesRequest.getStepTransLinesId());
//            stepTransLinesRequest.setStepStatus(linesRequest.getStepStatus());
//            stepTransLinesRequest.setRemarks(linesRequest.getRemarks());
//            stepTransService.updateLines(stepTransLinesRequest);
//        }
//        return "redirect:/step-trans";
//    }
    @GetMapping("/update-lines")
    public String updateLines(@RequestParam(name = "stepTransLineId") Long stepTransLineId,
                              @RequestParam(name = "stepStatus") String stepStatus,
                              @RequestParam(name = "pick", required = false) Integer pick) {
        if (stepTransLineId != null) {
            StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
            stepTransLinesRequest.setStepTransLinesId(stepTransLineId);
            stepTransLinesRequest.setStepStatus(stepStatus);
            stepTransLinesRequest.setPick(pick);
            var res = stepTransService.updateLines(stepTransLinesRequest);
        }
        return "redirect:/step-trans";
    }
//    // optional: view single (if you want to implement a detail page later)
//    @GetMapping("/{id}")
//    public String viewById(@PathVariable("id") Long id, Model model) {
//        StepTransResponse response = stepTransService.findById(id);
//        model.addAttribute("stepTransResponse", response);
//        return "step-trans-detail"; // optional template
//    }
}
