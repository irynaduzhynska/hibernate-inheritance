package basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mentor_id")
public class Mentor extends Person {
    public Mentor() {
    }

    public Mentor(int age, String name) {
        super(age, name);
    }
}
