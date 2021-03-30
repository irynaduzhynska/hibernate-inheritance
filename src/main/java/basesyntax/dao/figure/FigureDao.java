package basesyntax.dao.figure;

import basesyntax.model.figure.Figure;

import java.util.List;

public interface FigureDao<T extends Figure> {
    T save(T figure);

    List<T> findByColor(String color, Class<T> clazz);

    List<T> getAll(Class<T> clazz);

    List<T> findById(Long id, Class<T> clazz);
}
