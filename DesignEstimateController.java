package com.sjcarpentry.web;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.JobTypes;
import com.sjcarpentry.JobTypes.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/estimateform")
public class DesignEstimateController {


    @GetMapping
    public String showEstimateForm()
    {
        return "estimateform";
    }

    @PostMapping
    public String processEstimate(@Valid @ModelAttribute("estiamteform") Estimate estimateform, Errors errors){
        if (errors.hasErrors())
            return "estimateform";
        //save our estimate form

        log.info("Processing..." + estimateform);
        return "redirect:/submit/current";
    }



    @ModelAttribute
    public void addAttributes(Model model) {
        List<JobTypes> JobTypes = createFormList();
        Type[] types = Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(JobTypes, type));
        }
        model.addAttribute("estimateform", new Estimate());
    }

    private Object filterByType(List<JobTypes> JobTypes, Type type) {
        return JobTypes
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    private List<JobTypes> createFormList() {
        List<JobTypes> jobTypes = Arrays.asList(
                new JobTypes("TILE", "Bathroom Tiling", Type.Bathroom),
                new JobTypes("SHWR", "Shower Tiling", Type.Bathroom),
                new JobTypes("APPL", "Kitchen Appliance Installation", Type.Kitchen),
                new JobTypes("FlOR", "Kitchen Floor Tiling", Type.Kitchen),
                new JobTypes("SHIN","Roof Shingles", Type.Roof),
                new JobTypes("RCLN","Roof Cleanup", Type.Roof),
                new JobTypes("KPLM","Kitchen Plumbing", Type.Plumbing),
                new JobTypes("BPLM","Bathroom Plumbing", Type.Plumbing)

        );
        return jobTypes;
    }
}
