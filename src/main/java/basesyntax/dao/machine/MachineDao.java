package basesyntax.dao.machine;

import basesyntax.model.machine.Machine;

import java.util.List;

public interface MachineDao {
    Machine save(Machine machine);

    List<Machine> findByAgeOlderThan(int age);
}
