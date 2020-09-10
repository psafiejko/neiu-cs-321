package com.sjcarpentry.web;

import com.sjcarpentry.formData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping
public class DesignEstimateController {


    @GetMapping
    public String showEstimateForm()
    {
        return "Estimate";
    }

    @ModelAttribute
    public void addAttributes(Model model)
    {
        List<formData> formData = createFormList();

    }

    private List<formData> createFormList() {

        List<formData> estimateData = Arrays.asList(
            new formData("Piotr","220 Pinecroft","7735045726","psafiejko@neiu.edu","Bathroom"),
                new formData("Jack","221 Bridgeview", "7732591770","jacksafiejko@yahoo.com","Kitchen"),
                new formData("Ewa","222 Newport","7736599651","ewasafiejko@op.pl","Painting"));

        return estimateData;
    }
}
