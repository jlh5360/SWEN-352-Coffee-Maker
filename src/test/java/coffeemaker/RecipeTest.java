package coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import coffeemaker.domain.Recipe;
import coffeemaker.exceptions.RecipeException;

public class RecipeTest {

    // unused for task 1 for now, delete this comment later
    @SuppressWarnings("unused")
    private Recipe recipe;

    // 0 code coverage - Task 1
     @BeforeEach
    public void setUp() {
        recipe = new Recipe();
    }

    @Test
    @DisplayName("Sample test: Task 1")
    public void sampleTest() {
        assertTrue(true);
    }

    // Task 3: find 4 defects

    // Recipe should not be able to not have coffee 
    // in the future, should add a test for milk, sugar, and chocolate too
    @Test
    @DisplayName("Test: Negative content of coffee")
    public void testNegativeCoffeeContent() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("-1");
        }, "A negative content of coffee is not allowed.");
    }

    // Test for price boundary
    @Test
    @DisplayName("Test: negative cost of coffee")
    public void testNegativePrice() {
        // throws exception for error if the price is in the negatives
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("-1");
        }, "Prices for coffee can not be negative.");
    }

    // Test for setting price amount
    @Test
    @DisplayName("Test: price was not set correctly")
    public void testPriceErrorMessage() {
        RecipeException exception = assertThrows(RecipeException.class, () -> {
            recipe.setPrice("-5");
        });
        assertEquals("Price must be a positive integer", exception.getMessage());
    }

    // Test for empty name
    @Test
    @DisplayName("Test: empty name is not allowed")
    public void testSetBlankName() {
        recipe.setName("   "); // whitespace
        assertNotEquals("   ", recipe.getName(),
        "Empty or blank names are not allowed");
    }


}
