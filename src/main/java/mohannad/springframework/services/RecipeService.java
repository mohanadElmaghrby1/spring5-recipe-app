package mohannad.springframework.services;

import mohannad.springframework.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe>getRecipes();
}
