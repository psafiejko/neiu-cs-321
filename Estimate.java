package com.sjcarpentry;
import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
public class Estimate{

    @NotNull(message = "Name is required")
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull(message = "Phone number is required")
    @Size(min = 9, message = "Phone number must be 9 characters long")
    private String phone;

    @NotNull(message = "Please provide a description of your issue")
    @Size(min = 20, message = "Please provide more details")
    private String description;

    @NotEmpty(message = "You must choose at least 1 job")
    private List<String> jobs;



}
