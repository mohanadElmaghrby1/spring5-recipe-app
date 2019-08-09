package mohannad.springframework.commands;

import mohannad.springframework.model.Recipe;

import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * created by mohannad
 */
public class NotesCommand {
    private Long id;
    private RecipeCommand recipe;
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipeCommand getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeCommand recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
