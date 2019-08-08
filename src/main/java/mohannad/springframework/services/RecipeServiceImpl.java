package mohannad.springframework.services;

import mohannad.springframework.model.Recipe;
import mohannad.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl  implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     *
     * @return all recipes in the data base
     */
    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe>recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(recipeSet::add);
        return recipeSet;
    }

    /**
     *
     * @param id of the required recipe
     * @return exception if not found and recipe if found
     */
    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        //check if not found then throw exception
        if (!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
        }

        //return the desired  recipe
        return recipeOptional.get();
    }
}
