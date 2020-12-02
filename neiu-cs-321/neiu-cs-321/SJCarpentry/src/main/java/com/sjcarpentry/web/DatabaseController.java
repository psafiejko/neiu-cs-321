package com.sjcarpentry.web;

import com.sjcarpentry.User;
import com.sjcarpentry.data.EstimateRepository;
import com.sjcarpentry.data.JobTypestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;

@Controller
@RequestMapping("/results")
@Slf4j
public class DatabaseController {

    private JobTypestRepository jobRepo;
    private EstimateRepository estimateRepo;

   @Autowired
   public DatabaseController(JobTypestRepository jobRepo, EstimateRepository estimateRepo) {
       this.jobRepo = jobRepo;
       this.estimateRepo = estimateRepo;
   }

    @GetMapping
    public String showData(){

        return "data_view";
    }

    @ModelAttribute
    public void addData (Model model, @AuthenticationPrincipal User user)
    {

    }
}
