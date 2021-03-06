package basesyntax.dao.ma;

import basesyntax.dao.AbstractDao;
import basesyntax.exception.DataProcessingException;
import basesyntax.model.ma.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PersonDaoImpl extends AbstractDao implements PersonDao {
    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person save(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
            return person;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert to DB person: " + person, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
