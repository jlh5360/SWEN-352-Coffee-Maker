package coffeemaker.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecipeBookTest {
    @Test
    @DisplayName("Default Constructor")
    public void testDefaultConstructor() {
        assertNotNull(new RecipeBook());
    }

    @Test
    @DisplayName("getRecipes empty array")
    public void testGetRecipes() {
        RecipeBook recipeBook = new RecipeBook();

        assertArrayEquals(new Recipe[4], recipeBook.getRecipes());
    }

    @Test
    @DisplayName("getRecipes empty array")
    public void testGetRecipesNonEmpty() {
        RecipeBook recipeBook = new RecipeBook();

        Recipe milk = new Recipe();

        milk.setName("milk");

        recipeBook.addRecipe(milk);

        assertArrayEquals(new Recipe[]{milk, null, null, null}, recipeBook.getRecipes());
    }

    @Test
    @DisplayName("addRecipe adding new recipe")
    public void testAddRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        assertTrue(recipeBook.addRecipe(new Recipe()));
    }

    @Test
    @DisplayName("addRecipe adding identical recipe")
    public void testAddSameRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        recipeBook.addRecipe(new Recipe());
        
        assertFalse(recipeBook.addRecipe(new Recipe()));
    }

    @Test
    @DisplayName("addRecipe adding unique recipe")
    public void testAddUniqueRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        Recipe milk = new Recipe();
        Recipe water = new Recipe();

        milk.setName("milk");
        water.setName("water");

        recipeBook.addRecipe(milk);
        recipeBook.addRecipe(water);

        assertArrayEquals(new Recipe[]{milk, water, null, null}, recipeBook.getRecipes());
    }

    @Test
    @DisplayName("addRecipe adding recipe to full recipe book")
    public void testAddRecipeFullBook() {
        RecipeBook recipeBook = new RecipeBook();

        Recipe milk = new Recipe();
        Recipe water = new Recipe();
        Recipe chips = new Recipe();
        Recipe rice = new Recipe();

        milk.setName("milk");
        water.setName("water");
        chips.setName("chips");
        rice.setName("rice");

        recipeBook.addRecipe(milk);
        recipeBook.addRecipe(water);
        recipeBook.addRecipe(chips);
        recipeBook.addRecipe(rice);

        assertFalse(recipeBook.addRecipe(new Recipe()));
    }

    @Test
    @DisplayName("deleteRecipe deleting recipe")
    public void testDeleteRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        Recipe milk = new Recipe();

        milk.setName("milk");

        recipeBook.addRecipe(milk);

        assertEquals("milk", recipeBook.deleteRecipe(0));
    }

    @Test
    @DisplayName("deleteRecipe deleting recipe that does not exist")
    public void testDeleteNonExistientRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    @DisplayName("deleteRecipe trying to delete recipe in out of bounds index")
    public void testDeleteOutOfBoundsRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        assertThrows(IndexOutOfBoundsException.class, () -> recipeBook.deleteRecipe(5));
    }

    @Test
    @DisplayName("deleteRecipe adding and deleting recipe")
    public void testAddDeleteRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        Recipe milk = new Recipe();

        milk.setName("milk");

        recipeBook.addRecipe(milk);
        recipeBook.deleteRecipe(0);

        assertArrayEquals(new Recipe[4], recipeBook.getRecipes());
    }

    @Test
    @DisplayName("replaceRecipe replacing recipe")
    public void testReplaceRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        Recipe milk = new Recipe();
        Recipe water = new Recipe();

        milk.setName("milk");
        water.setName("water");

        recipeBook.addRecipe(milk);

        assertEquals("milk", recipeBook.replaceRecipe(0, water));
    }

    @Test
    @DisplayName("replaceRecipe replacing recipe with no recipe in the slot")
    public void testReplaceEmptySlotRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        Recipe milk = new Recipe();

        milk.setName("milk");

        assertNull(recipeBook.replaceRecipe(0, milk));
    }

    @Test
    @DisplayName("replaceRecipe trying to replace recipe in out of bounds index")
    public void testReplaceOutOfBoundsRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        assertThrows(IndexOutOfBoundsException.class, () -> recipeBook.replaceRecipe(5, new Recipe()));
    }
}
