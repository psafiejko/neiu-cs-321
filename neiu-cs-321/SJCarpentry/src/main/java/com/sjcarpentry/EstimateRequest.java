package com.sjcarpentry;

import lombok.Data;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.Getter;


@Data
public class EstimateRequest {

    @Getter(AccessLevel.PACKAGE) private final String name;
    @Getter(AccessLevel.PACKAGE) private final String address;
    @Getter(AccessLevel.PACKAGE) private final String phone;
    @Getter(AccessLevel.PACKAGE) private final String email;
    @Getter(AccessLevel.PACKAGE) private final String jobType;

}
