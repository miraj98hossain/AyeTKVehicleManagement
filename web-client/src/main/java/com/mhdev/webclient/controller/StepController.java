package com.mhdev.webclient.controller;

import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
import com.mhdev.webclient.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/steps")
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping
    public String listSteps(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {

        Page<StepResponse> stepPage = stepService.getSteps(PageRequest.of(page, size));
        model.addAttribute("stepsList", stepPage.getContent());
        model.addAttribute("step", new StepRequest());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", stepPage.getTotalPages());

        return "steps-page";
    }


    @PostMapping
    public String saveStep(@ModelAttribute("step") StepRequest step) {
        if (step.getStepId() == null) {
            stepService.saveStep(step);
        } else {
            stepService.updateStep(step);
        }
        return "redirect:/steps";
    }

    @GetMapping("/edit/{id}")
    public String editStep(@PathVariable Long id, Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {

        StepResponse existingStep = stepService.getStep(id);
        Page<StepResponse> stepPage = stepService.getSteps(PageRequest.of(page, size));

        StepRequest stepRequest = new StepRequest();
        stepRequest.setStepId(existingStep.getStepId());
        stepRequest.setStepName(existingStep.getStepName());
        stepRequest.setIsActive(existingStep.getIsActive());

        model.addAttribute("step", stepRequest);
        model.addAttribute("stepsList", stepPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", stepPage.getTotalPages());

        return "steps-page";
    }

}


