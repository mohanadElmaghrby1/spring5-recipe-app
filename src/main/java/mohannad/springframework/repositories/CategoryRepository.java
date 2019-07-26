package mohannad.springframework.repositories;

import mohannad.springframework.model.Category;
import mohannad.springframework.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    //naming convension findBy+property name
    Optional<Category> findByDescription(String description);

}
