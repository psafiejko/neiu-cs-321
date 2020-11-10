package com.sjcarpentry.data;


import com.sjcarpentry.JobTypes;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobTypestRepository extends CrudRepository<JobTypes, String> {


}

