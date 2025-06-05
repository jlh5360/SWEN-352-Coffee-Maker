package coffeemaker.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecipeBookTest {
    @Test
    @DisplayName("Default Constructor")
    public void testDefaultConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RecipeBook());
    }

    // @Test
    // @DisplayName("getRecipes empty array")
    // public void testGetRecipes() {
    //     RecipeBook recipeBook = new RecipeBook();
    // }
}
