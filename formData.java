package com.sjcarpentry;

import lombok.Data;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.Getter;


@Data
public class formData {

    @Getter(AccessLevel.PACKAGE) private final String name;
    @Getter(AccessLevel.PACKAGE) private final String address;
    @Getter(AccessLevel.PACKAGE) private final int phone;
    @Getter(AccessLevel.PACKAGE) private final String email;
    @Getter(AccessLevel.PACKAGE) private final String jobType;

    @ToString(includeFieldNames = true)
    @Data(staticConstructor = "of")
    public static class estimate<T>
    {
        private final String name;
        private final T value;
    }
}
