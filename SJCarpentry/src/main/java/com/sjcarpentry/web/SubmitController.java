package com.sjcarpentry.web;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.User;
import com.sjcarpentry.data.EstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/submit")
@SessionAttributes("submit")
public class SubmitController {

    private EstimateRepository estimateRepo;

    @Autowired
    public SubmitController(EstimateRepository estimateRepo){
        this.estimateRepo = estimateRepo;
    }

    @GetMapping("/current")
    public String redirectform(){
        return "redirectform";
    }

    @PostMapping
    public String submission (@Valid @ModelAttribute("estimate")Estimate estimate, Errors errors, @AuthenticationPrincipal User user)
    {
        if(errors.hasErrors())
            return "redirectform";

        estimate.setUser(user);

        return "redirect:/";
    }

}
