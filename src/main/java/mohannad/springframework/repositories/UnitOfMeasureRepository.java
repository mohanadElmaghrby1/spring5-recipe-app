package mohannad.springframework.repositories;

import mohannad.springframework.model.Category;
import mohannad.springframework.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure , Long > {
    //naming convension findBy+property name
    Optional<UnitOfMeasure> findByDescription(String description);
}
