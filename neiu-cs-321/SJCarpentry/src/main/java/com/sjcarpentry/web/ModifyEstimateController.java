package com.sjcarpentry.web;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.Job_Types;
import com.sjcarpentry.data.EstimateRepository;
import com.sjcarpentry.data.JobTypestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/modify")
public class ModifyEstimateController {

    private final EstimateRepository estimateRepo;
    private final JobTypestRepository jobtypeRepo;


    @Autowired
    public ModifyEstimateController(JobTypestRepository jobtypeRepo, EstimateRepository estimateRepo) {
        this.jobtypeRepo = jobtypeRepo;
        this.estimateRepo = estimateRepo;
    }


    @GetMapping("/{estimateId}")
    public  String updateEstimate(@PathVariable("estimateId") long id, Model model) {
        Estimate estimate = estimateRepo.findById(id).get();
        model.addAttribute("jobtypesIds", getJobTypesId(estimate));
        model.addAttribute("estimate", estimate);
        addJobTypesToModel(model);
        return "update-estimate";
    }

    @PostMapping("/update/{estimateId}")
    public String processUpdateEstimate(@PathVariable("estimateId") long id, @Valid @ModelAttribute("estimate") Estimate estimate, Errors errors) {
        if(errors.hasErrors())
            return "update-estimate";

        Estimate newEstimate = estimateRepo.findById(id).get();
        newEstimate.setJobs(estimate.getJobs());
        newEstimate.setName(estimate.getName());
        estimateRepo.save(newEstimate);
        log.info("Processing..." + newEstimate);
        return "redirect:/results";
    }

    private List<String> getJobTypesId(Estimate estimate) {
        List<Job_Types> JobTypes = estimate.getJobs();
        List<String> jobtypesIds = new ArrayList<>();
        for (Job_Types job_types: JobTypes) {
            jobtypesIds.add(job_types.getId());
        }
        return jobtypesIds;
     }

    private void addJobTypesToModel(Model model) {
        List<Job_Types> JobTypes = (List<Job_Types>)jobtypeRepo.findAll();
        Job_Types.Type[] types = Job_Types.Type.values();
        for (Job_Types.Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(JobTypes, type));
        }

        model.addAttribute("estimate", new Estimate());
    }


    private Object filterByType(List<Job_Types> JobTypes, Job_Types.Type type) {
        return JobTypes
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}

