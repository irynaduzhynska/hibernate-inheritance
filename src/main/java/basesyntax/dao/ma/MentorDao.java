package basesyntax.dao.ma;

import basesyntax.model.ma.Mentor;

import java.util.List;

public interface MentorDao extends PersonDao {
    List<Mentor> findByAgeGreaterThan(int age);
}
