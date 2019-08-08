package mohannad.springframework.controllers;

import mohannad.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by mohannad
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"/recipe/show/{id}"})
    public String getRecipe(@PathVariable String id ,  Model model){
        model.addAttribute("recipe" , recipeService.findById(new Long(id)));
        return "recipe/show";
    }
}
