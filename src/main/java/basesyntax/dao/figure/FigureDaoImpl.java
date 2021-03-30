package basesyntax.dao.figure;

import basesyntax.dao.AbstractDao;
import basesyntax.exception.DataProcessingException;
import basesyntax.model.figure.Figure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert to DB figure: " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from core.basesyntax.model.figure.Figure f "
                    + "where f.color = :color", clazz).setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find figure: " + clazz
                    + " by color: " + color, e);
        }
    }

    @Override
    public List<T> getAll(Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from core.basesyntax.model.figure.Figure",
                    clazz).getResultList();
        }
    }

    @Override
    public List<T> findById(Long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from core.basesyntax.model.figure.Figure f "
                    + "where f.id = :id", clazz).setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find figure: " + clazz
                    + " by id: " + id, e);
        }
    }
}
