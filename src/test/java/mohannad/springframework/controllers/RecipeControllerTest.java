package mohannad.springframework.controllers;

import mohannad.springframework.commands.RecipeCommand;
import mohannad.springframework.model.Recipe;
import mohannad.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    //create Model mock for using when we calling getIndexPage(Model model)
    @Mock
    Model model;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        //setup mocks , telling mockito that we want RecipeService mock
        MockitoAnnotations.initMocks(this);

        //create mock mvc
         mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        recipeController=new RecipeController(recipeService);
    }

    @Test
    public void getRecipe() throws Exception {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        //test mock mvc
        mockMvc.perform(get("/recipe/show/1")).
                andExpect(status().isOk()).
                andExpect(view().name("recipe/show"));

    }

//    @Test
//    public void testPostNewRecipeForm() throws Exception {
//
//        RecipeCommand recipeCommand = new RecipeCommand();
//        recipeCommand.setId(2L);
//
//        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
//        //test mock mvc
//        mockMvc.perform(post("/recipe"))
//
////                .andExpect(status().isOk()).
////                andExpect(view().name("recipe/show"));
//
//    }
}