package com.sjcarpentry;
import lombok.Data;

/*import javax.persistence.Entity;
import javax.persistence.Id;*/

//@Entity
@Data
public class JobTypes {

//    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        KITCHEN, BATHROOM, ROOF, PLUMBING
    }
}
