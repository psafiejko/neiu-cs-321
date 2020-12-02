package com.sjcarpentry.web;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.Job_Types;
import com.sjcarpentry.Job_Types.Type;
import com.sjcarpentry.User;
import com.sjcarpentry.data.EstimateRepository;
import com.sjcarpentry.data.JobTypestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/form")
public class DesignEstimateController {

    private final JobTypestRepository jobtypeRepo;
    private final EstimateRepository estimateRepo;


    @Autowired
    public DesignEstimateController(JobTypestRepository jobtypeRepo, EstimateRepository estimateRepo) {
        this.jobtypeRepo = jobtypeRepo;
        this.estimateRepo = estimateRepo;
    }


    @GetMapping
    public String showEstimateForm(Model model, @AuthenticationPrincipal User user)
    {
        addUserInfoToModel(model, user);
        return "estimate_form";
    }

    private void addUserInfoToModel(Model model, User user) {
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("street", user.getStreet());
        model.addAttribute("city", user.getCity());
        model.addAttribute("state", user.getState());
        model.addAttribute("zip", user.getZip());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
    }

    @PostMapping
    public String processEstimate(@Valid @ModelAttribute("estimate") Estimate estimate, Errors errors){
        if (errors.hasErrors())
            return "estimate_form";

        Estimate savedEstimate = estimateRepo.save(estimate);
        log.info("Processing..." + estimate);
        return "redirect:/submit/current";
    }



    @ModelAttribute
    public void addAttributes(Model model) {
        List<Job_Types> JobTypes = (List<Job_Types>)jobtypeRepo.findAll();
        Type[] types = Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(JobTypes, type));
        }

        model.addAttribute("estimate", new Estimate());
    }

    @ModelAttribute(name = "estimate")
    public Estimate addEstimatetoModel() {
        return new Estimate();
    }

    private Object filterByType(List<Job_Types> JobTypes, Type type) {
        return JobTypes
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    }

