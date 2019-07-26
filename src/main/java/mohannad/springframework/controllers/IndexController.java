package mohannad.springframework.controllers;

import mohannad.springframework.model.Category;
import mohannad.springframework.model.UnitOfMeasure;
import mohannad.springframework.repositories.CategoryRepository;
import mohannad.springframework.repositories.UnitOfMeasureRepository;
import mohannad.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"" , "/" ,"/index","/index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes" , recipeService.getRecipes());
        return "index";
    }
}
