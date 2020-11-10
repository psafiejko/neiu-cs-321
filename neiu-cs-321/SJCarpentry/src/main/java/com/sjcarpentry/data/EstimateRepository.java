package com.sjcarpentry.data;

import com.sjcarpentry.Estimate;
import org.springframework.data.repository.CrudRepository;

public interface EstimateRepository extends CrudRepository<Estimate, Long> {

}
