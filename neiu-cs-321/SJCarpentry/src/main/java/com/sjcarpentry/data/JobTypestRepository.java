package com.sjcarpentry.data;


import com.sjcarpentry.Job_Types;
import org.springframework.data.repository.CrudRepository;

public interface JobTypestRepository extends CrudRepository<Job_Types, String> {


}

