package com.sjcarpentry.data;

import com.sjcarpentry.Estimate;
import com.sjcarpentry.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstimateRepository extends CrudRepository<Estimate, Long> {

    List<Estimate> findAllByUser(User user, Pageable pageable);
}

