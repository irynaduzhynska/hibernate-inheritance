package basesyntax.dao.ma;

import basesyntax.model.ma.Mentor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Mentor c where c.age > :age", Mentor.class)
                    .setParameter("age", age).getResultList();
        }
    }
}
