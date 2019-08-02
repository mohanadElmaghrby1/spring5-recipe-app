package mohannad.springframework.controllers;

import mohannad.springframework.model.Recipe;
import mohannad.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void mockMVC() throws Exception {
        //create mock mvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        //test mock mvc
        mockMvc.perform(get("/")).
                andExpect(status().isOk()).
                andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(new Recipe());
        recipeData.add(new Recipe());

        //when call recipeService.getRecipes() return instead recipeData
        when(recipeService.getRecipes()).thenReturn(recipeData);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = indexController.getIndexPage(model);
        //make sure that getIndexPage return the proper template name (index)
        assertEquals(viewName,"index");

        //verify that recipeService getRecipes is called one time
        verify(recipeService , times(1)).getRecipes();

        //make sure that model add attribute is called one time
        //eq("recipes") telling that we want to make sure that the first arg is equal recipes
        //anySet() generate any set
        verify(model , times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        //verify the passed set
        Set<Recipe>setIn = argumentCaptor.getValue();
        assertEquals(2 ,setIn.size() );

    }
}