package basesyntax.dao.animal;

import basesyntax.model.zoo.Animal;
import java.util.List;

public interface AnimalDao {
    Animal save(Animal animal);

    List<Animal> findByNameFirstLetter(Character character);

    List<Animal> getAll();
}
