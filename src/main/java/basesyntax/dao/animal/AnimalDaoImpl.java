package basesyntax.dao.animal;

import basesyntax.dao.AbstractDao;
import basesyntax.exception.DataProcessingException;
import basesyntax.model.zoo.Animal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert to DB animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Animal> query = criteriaBuilder.createQuery(Animal.class);
            Root<Animal> root = query.from(Animal.class);
            Predicate predicate = criteriaBuilder.like(root.get("name"), character + "%");
            query.select(root).where(predicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get animals with first name startWith: "
                    + character, e);
        }
    }

    public List<Animal> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Animal", Animal.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get animals from DB", e);
        }
    }
}
