package com.sjcarpentry.data;

import com.sjcarpentry.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,  Long> {

    User findByUsername(String username);
}
