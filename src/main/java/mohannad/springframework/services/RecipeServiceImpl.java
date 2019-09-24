package mohannad.springframework.services;

import mohannad.springframework.commands.RecipeCommand;
import mohannad.springframework.converters.RecipeCommandToRecipe;
import mohannad.springframework.converters.RecipeToRecipeCommand;
import mohannad.springframework.exceptions.NotFoundException;
import mohannad.springframework.model.Recipe;
import mohannad.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class RecipeServiceImpl  implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
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
            throw new NotFoundException("Recipe Not Found. for id value:"+id.toString());
        }

        //return the desired  recipe
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
//        recipeRepository.save(recipeCommandToRecipe.convert(command));
//        return command;

        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
//        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);

    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        Recipe recipe =recipeRepository.findById(id).get();
        return  recipeToRecipeCommand.convert(recipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

}
