package com.sjcarpentry.data;

import com.sjcarpentry.Estimate;
import org.springframework.data.repository.CrudRepository;

public interface EstimateRepository extends CrudRepository<Estimate, Long> {


}


//<span class="validationError"
//    th:if="${param.error}">Username or password is incorrect</span>