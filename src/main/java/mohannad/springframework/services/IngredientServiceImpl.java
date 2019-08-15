package mohannad.springframework.services;

import mohannad.springframework.commands.IngredientCommand;
import mohannad.springframework.converters.IngredientCommandToIngredient;
import mohannad.springframework.converters.IngredientToIngredientCommand;
import mohannad.springframework.converters.RecipeCommandToRecipe;
import mohannad.springframework.converters.RecipeToRecipeCommand;
import mohannad.springframework.model.Ingredient;
import mohannad.springframework.model.Recipe;
import mohannad.springframework.repositories.RecipeRepository;
import mohannad.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

/**
 * created by mohannad
 */
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        //get first ingeredient
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();

        return ingredientCommandOptional.get();

    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        //get recipe
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        //check if recipe is present
        if (!recipeOptional.isPresent()){
            //log error recipe not found
            return new IngredientCommand();
        }

        Recipe recipe = recipeOptional.get();
        //get ingredient
        Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();

        //check if ingredient is present
        if (ingredientOptional.isPresent()){
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(command.getDescription());
            ingredientFound.setAmount(command.getAmount());
            ingredientFound.setUom(unitOfMeasureRepository
                    .findById(command.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("UOM Not Found")));
        }else {
            //add new ingredient
            recipe.getIngredients().add(ingredientCommandToIngredient.convert(command));
        }

        Recipe saveRecipe = recipeRepository.save(recipe);

        return null;
    }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {

    }
}
