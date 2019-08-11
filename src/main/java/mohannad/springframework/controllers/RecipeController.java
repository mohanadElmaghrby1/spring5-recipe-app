package mohannad.springframework.controllers;

import mohannad.springframework.commands.RecipeCommand;
import mohannad.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * created by mohannad
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"/recipe/{id}/show"})
    public String getRecipe(@PathVariable String id ,  Model model){
        model.addAttribute("recipe" , recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){

        //create new recipe command and pass it to the view
        //to binding data in
        model.addAttribute("recipe" , new RecipeCommand());

        return "recipe/recipeform";
    }

    @RequestMapping({"/recipe/{id}/update"})
    public String updateRecipe(@PathVariable String id ,Model model){
        model.addAttribute("recipe" ,recipeService.findCommandById(new Long(id)));
        return "recipe/recipeform";
    }


    @RequestMapping({"/recipe/{id}/delete"})
    public String updateRecipe(@PathVariable String id){
        recipeService.deleteById(new Long(id));
//        log.debug("deleting");
        return "redirect:/";
    }


    /*handle the post back from /recipe/new */
    @PostMapping //    @RequestMapping(name = "recipe" , method = RequestMethod.POST) is the same
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        //save the returned recipecommand to db
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        //redirect to the recipe show
        return "redirect:/recipe/"+savedRecipeCommand.getId()+"/show";
    }
}
