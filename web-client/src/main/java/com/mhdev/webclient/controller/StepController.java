package com.mhdev.webclient.controller;

import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
import com.mhdev.webclient.service.ApiResponseConversionService;
import com.mhdev.webclient.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/steps")
public class StepController {

    @Autowired
    ApiResponseConversionService apiResponseConversionService;

    @Autowired
    private StepService stepService;

    @GetMapping
    public String listSteps(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {

        var res = stepService.getSteps(PageRequest.of(page, size));
        apiResponseConversionService.apiResponseConversion(res, model);
        model.addAttribute("stepResponse", new StepResponse());
        return "steps-page";
    }


    @PostMapping
    public String saveStep(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @ModelAttribute("stepResponse") StepResponse step) {

        StepRequest stepRequest = new StepRequest();
        stepRequest.setStepId(step.getStepId());
        stepRequest.setStepName(step.getStepName());
        stepRequest.setIsActive(step.getIsActive());

        if (step.getStepId() == null) {
            stepService.saveStep(stepRequest);
        } else {
            stepService.updateStep(stepRequest);
        }
        return "redirect:/steps";
    }

    @GetMapping("/edit/{id}")
    public String editStep(@PathVariable Long id, Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {

        var pdRes = stepService.getSteps(PageRequest.of(page, size));

        apiResponseConversionService.apiResponseConversion(pdRes, model);
        //--------------------------------------------------------------------
        var singleRes = stepService.getStep(id);
        apiResponseConversionService.apiResponseConversion(singleRes, model);

        return "steps-page";
    }
}


