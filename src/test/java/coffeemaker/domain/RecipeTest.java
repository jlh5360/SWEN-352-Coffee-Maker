// Test file Author: Almazan, Mary

package coffeemaker.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import coffeemaker.exceptions.RecipeException;

public class RecipeTest {

    // unused for task 1 for now, delete this comment later
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

////////////////////////////////////////////////////////////////////////////////
    //** Test for a blank recipe name */ 
    @Test
    @DisplayName("Test: Checking if user is attempting to input a blank recipe name")
    public void testNullNameThrowsException() {
        assertThrows(RecipeException.class, () -> {
            recipe.setName(null);
        }, "You must create a recipe name");
    }

    @Test
    @DisplayName("Test: Checking if user is attempting to enter an empty string")
    public void testEmptyStringNameThrowsException() {
        assertThrows(RecipeException.class, () -> {
            recipe.setName("");
        }, "You must create a recipe name");
    }

    @Test
    @DisplayName("Test: Checking if user is inputting whitespace only")
    public void testWhitespaceNameThrowsException() {
        assertThrows(RecipeException.class, () -> {
            recipe.setName("   ");
        }, "You must create a recipe name");
    }

    @Test
    @DisplayName("Test: Recipe Name Attempt")
    public void testValidNameWorks() throws RecipeException {
        recipe.setName("Espresso");
        assertEquals("Espresso", recipe.getName());
    }
////////////////////////////////////////////////////////////////////////////////
    //** Test for price input defects */
    @Test
    @DisplayName("Test: Can not input empty price")
    public void testNullPricet() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice(null);
        }, "You must input a valid price");
    }

    @Test
    @DisplayName("Test: Can not input an empty string for price")
    public void testEmptyPriceString() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("");
        }, "You must input a valid price");
    }

    @DisplayName("Test: Can not input whitespace only for price")
    public void testWhitespacePriceString() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("   ");
        }, "You must input a valid price");
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

    @Test
    @DisplayName("Test: Price can not have invalid characters, only intergers")
    public void testPriceInvalidChars() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("5abc");
        }, "Price can not have letters");
    }

    @Test
    @DisplayName("Test: Price can not be negative")
    public void testNegativePrice() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("-1");
        }, "You must input a valid price");
    }
////////////////////////////////////////////////////////////////////////////////
    //** Test for milk content defects */
    @Test
    @DisplayName("Test: Valid milk amount")
    public void testValidMilkAmount() {
        assertDoesNotThrow(() -> {
            recipe.setAmtMilk("2");
            assertEquals(2, recipe.getAmtMilk());
        });
    }

    @Test
    @DisplayName("Test: Milk content can not be zero")
    public void testNegativeMilkAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtMilk("-1");
        }, "Can not input negative interger");
    }

    @Test
    @DisplayName("Test: Must insert a milk amount")
    public void testInvalidMilkString() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtMilk("xyz");
        }, "Input a valid amount for milk");
    }

    @Test
    @DisplayName("Test: Empty milk amount string")
    public void testEmptyMilkString() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtMilk("");
        }, "Input a valid amount for milk");
    }


}
