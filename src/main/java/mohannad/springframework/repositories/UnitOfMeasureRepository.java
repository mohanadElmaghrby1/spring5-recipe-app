package mohannad.springframework.repositories;

import mohannad.springframework.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure , Long> {
}
