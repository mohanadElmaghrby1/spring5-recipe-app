package mohannad.springframework.controllers;

import mohannad.springframework.commands.RecipeCommand;
import mohannad.springframework.exceptions.NotFoundException;
import mohannad.springframework.model.Recipe;
import mohannad.springframework.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        Recipe recipe =recipeService.findById(new Long(id));
        model.addAttribute("recipe" , recipe);
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
    public String deleteRecipe(@PathVariable String id){
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

    /**
     * ExceptionHandler method for NotFoundException class HttpStatus.NOT_FOUND 404
     * @return specific view for NOt_FOUND exception
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ModelAndView modelAndView(Exception exception){
        System.out.println("handel not found exception");
        System.out.println(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception" , exception);
        return modelAndView;
    }
}
