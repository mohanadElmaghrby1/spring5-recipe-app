package mohannad.springframework.controllers;

import mohannad.springframework.commands.IngredientCommand;
import mohannad.springframework.commands.RecipeCommand;
import mohannad.springframework.services.IngredientService;
import mohannad.springframework.services.IngredientServiceImpl;
import mohannad.springframework.services.RecipeService;
import mohannad.springframework.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by mohannad
 */
@Controller
public class IngredientController  {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
//        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        RecipeCommand recipeCommand =recipeService.findCommandById(Long.valueOf(recipeId));
        model.addAttribute("recipe", recipeCommand);

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,@PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient" , ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId)
                ,new Long(ingredientId)));
        return "recipe/ingredient/show";
    }
}
