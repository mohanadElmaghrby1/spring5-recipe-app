package mohannad.springframework.services;

import javafx.beans.binding.When;
import mohannad.springframework.model.Recipe;
import mohannad.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    //we need RecipeServiceImpl obj
    RecipeServiceImpl recipeService;

    //inside RecipeServiceImpl we need
    @Mock //indicate that we want mock this object
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        //setup mocks , telling mockito that we want RecipeRepository mock
        MockitoAnnotations.initMocks(this);

        //create a RecipeServiceImpl obj using mock
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        //mockito will create a mock with empty values
        Set<Recipe>recipes = recipeService.getRecipes();

        assertEquals(recipes.size() , 0);

        //what if we want to add recipe to the mock
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        //telling mockito when we call getRecipes return this
        when(recipeService.getRecipes()).thenReturn(recipeData);

        //calling getRecipec again to return recipesData
        recipes = recipeService.getRecipes();

        //now size should be one
        assertEquals(recipes.size() , 1);

        /*testing interactions with mocks
        make sure that recipeRepository findAll() method get called number of times
        in our example its called 2 times , one before using when and the other after using when
        to make sure that when working well
        */
        verify(recipeRepository , times(2)).findAll();

    }
}