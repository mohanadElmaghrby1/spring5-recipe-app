package mohannad.springframework.services;

import mohannad.springframework.commands.IngredientCommand;
import mohannad.springframework.model.Ingredient;

import java.util.Set;

/**
 * created by mohannad
 */
public interface IngredientService  {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long idToDelete);
}
