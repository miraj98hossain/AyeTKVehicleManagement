package com.mhdev.webclient.controller;

import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import com.mhdev.webclient.service.StepSetupService;
import com.mhdev.webclient.service.StepTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;
    @Autowired
    private StepSetupService stepSetupService;

    @Autowired
    public StepTransController(StepTransService stepTransService) {
        this.stepTransService = stepTransService;
    }

    @GetMapping
    public String listStepTrans(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        model.addAttribute("setupList", stepSetupService.getAllStepsSetup());

        Page<StepTransResponse> stepTransPage = stepTransService.findAll(PageRequest.of(page, size));
        if (stepTransPage == null) {
            model.addAttribute("stepTransList", Collections.emptyList());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", 0);
        } else {
            stepTransPage.getContent();
            model.addAttribute("stepTransList", stepTransPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", stepTransPage.getTotalPages());
        }

        model.addAttribute("stepTrans", new StepTransRequest());            // for creation form
        model.addAttribute("linesRequest", new StepTransLinesRequest());    // for update-lines form (can be reused per row)


        return "steps-trans-page";
    }

    // create a new StepTrans
    @PostMapping("/create")
    public String createNewStepTrans(@ModelAttribute("stepTrans") StepTransRequest stepTrans) {
        if (stepTrans.getStepTransId() == null) {
            stepTransService.create(stepTrans);
        }
        return "redirect:/step-trans";
    }

    // update step trans lines
    @PostMapping("/update-lines")
    public String updateLines(@ModelAttribute("linesRequest") StepTransLinesRequest linesRequest) {
        if (linesRequest.getStepTransLinesId() != null) {
            stepTransService.updateLines(linesRequest);
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
