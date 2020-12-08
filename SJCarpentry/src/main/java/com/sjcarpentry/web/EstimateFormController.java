package com.sjcarpentry.web;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.Job_Types;
import com.sjcarpentry.Job_Types.Type;
import com.sjcarpentry.User;
import com.sjcarpentry.data.EstimateRepository;
import com.sjcarpentry.data.JobTypesRepository;
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
public class EstimateFormController {

    private final JobTypesRepository jobtypeRepo;
    private final EstimateRepository estimateRepo;


    @Autowired
    public EstimateFormController(JobTypesRepository jobtypeRepo, EstimateRepository estimateRepo) {
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
    public String processEstimate(@Valid @ModelAttribute("estimate") Estimate estimate, Errors errors, @AuthenticationPrincipal User user){
        if (errors.hasErrors())
            return "estimate_form";

        estimate.addUserToEstimate(user);
        Estimate savedEstimate = estimateRepo.save(estimate);
        return "redirect:/results";
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        List<Job_Types> JobTypes = (List<Job_Types>)jobtypeRepo.findAll();
        Type[] types = Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(JobTypes, type));
        }

        //model.addAttribute("estimate", new Estimate());
    }

    @ModelAttribute(name = "estimate")
    public Estimate addEstimateToModel() {
        return new Estimate();
    }

    @ModelAttribute(name = "username")
    public String addUserNameToModel (@AuthenticationPrincipal User user) {return user.getFullName();}

    private Object filterByType(List<Job_Types> JobTypes, Type type) {
        return JobTypes
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    }

