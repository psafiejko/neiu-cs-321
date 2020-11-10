package com.sjcarpentry.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/results")
public class DatabaseController {

    @GetMapping("/current")
    public String redirectform(){
        return "dataview";
    }
}
