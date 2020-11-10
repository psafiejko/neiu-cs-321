package com.sjcarpentry;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class  Estimate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdAt;

    @NotNull(message = "Name is required")
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull(message = "Phone number is required")
    @Size(min = 9, message = "Phone number must be 9 characters long")
    private String phone;

    @NotNull(message = "Please provide a description of your issue")
    @Size(min = 20, message = "Please provide more details")
    private String description;

    @ManyToMany(targetEntity = JobTypes.class)
    @NotEmpty(message = "You must choose at least 1 job")
    private List<JobTypes> jobs;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

}
