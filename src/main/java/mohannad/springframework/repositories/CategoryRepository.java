package mohannad.springframework.repositories;

import mohannad.springframework.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
