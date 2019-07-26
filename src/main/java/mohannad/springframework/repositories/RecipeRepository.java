package mohannad.springframework.repositories;

import mohannad.springframework.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe , Long> {
}
