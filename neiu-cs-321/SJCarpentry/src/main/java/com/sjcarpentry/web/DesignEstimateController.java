package com.sjcarpentry.web;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.JobTypes;
import com.sjcarpentry.JobTypes.Type;
import com.sjcarpentry.data.EstimateRepository;
import com.sjcarpentry.data.JobTypestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showEstimateForm()
    {
        return "estimateform";
    }

    @PostMapping
    public String processEstimate(@Valid @ModelAttribute("estimate") Estimate estimate, Errors errors){
        if (errors.hasErrors())
            return "estimateform";

        Estimate savedEstimate = estimateRepo.save(estimate);
        log.info("Processing..." + estimate);
        return "redirect:/submit/current";
    }



    @ModelAttribute
    public void addAttributes(Model model) {
        List<JobTypes> JobTypes = (List<JobTypes>)jobtypeRepo.findAll();
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

    private Object filterByType(List<JobTypes> JobTypes, Type type) {
        return JobTypes
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    }

