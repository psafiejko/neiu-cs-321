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

    @NotEmpty(message = "You must choose at least 1 job")
    private List<JobTypes> JobTypes;


    public Estimate() {

    }
}
