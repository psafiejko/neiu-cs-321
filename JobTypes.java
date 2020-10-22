package com.sjcarpentry;
import lombok.Data;

@Data
public class JobTypes {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        Kitchen, Bathroom, Roof, Plumbing
    }
}
