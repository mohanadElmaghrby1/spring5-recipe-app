package mohannad.springframework.controllers;

import mohannad.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {

    IndexController indexController;
    @Mock
    RecipeService recipeService;

    //create Model mock for using when we calling getIndexPage(Model model)
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        //setup mocks , telling mockito that we want RecipeService mock
        MockitoAnnotations.initMocks(this);

        indexController=new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        //make sure that getIndexPage return the proper template name (index)
        assertEquals(indexController.getIndexPage(model),"index");


        //make sure that model add attribute is called one time
        //eq("recipes") telling that we want to make sure that the first arg is equal recipes
        //anySet() generate any set
        verify(model , times(1)).addAttribute(eq("recipes"), anySet());

        //verify that recipeService getRecipes is called one time
        verify(recipeService , times(1)).getRecipes();

    }
}