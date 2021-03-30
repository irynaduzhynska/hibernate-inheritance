package basesyntax.dao.ma;

import basesyntax.model.ma.Coach;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Coach c where c.experience > :years", Coach.class)
                    .setParameter("years", years).getResultList();
        }
    }
}
