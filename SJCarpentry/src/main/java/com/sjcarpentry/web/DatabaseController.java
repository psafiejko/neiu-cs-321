package com.sjcarpentry.web;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.User;
import com.sjcarpentry.data.EstimateRepository;
import com.sjcarpentry.data.JobTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/results")
@Slf4j
public class DatabaseController {

    private JobTypesRepository jobRepo;
    private EstimateRepository estimateRepo;
    private DataProperties dataProperties;

   @Autowired
   public DatabaseController(JobTypesRepository jobRepo, EstimateRepository estimateRepo, DataProperties dataProperties) {
       this.jobRepo = jobRepo;
       this.estimateRepo = estimateRepo;
       this.dataProperties = dataProperties;
   }

    @GetMapping
    public String showData(){

        return "data_view";
    }

    @ModelAttribute
    public void addData (Model model, @AuthenticationPrincipal User user)
    {

        Pageable pageable = PageRequest.of(0, dataProperties.getPageSize());
        List<Estimate> allData = estimateRepo.findAllByUser(user, pageable);
        model.addAttribute("jobs", allData);

        String fullName = user.getFullName();
        model.addAttribute("fullName", fullName);

    }
}
