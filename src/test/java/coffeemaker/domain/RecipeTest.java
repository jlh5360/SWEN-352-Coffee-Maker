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
////////////////////////////////////////////////////////////////////////////////
    // **  Tests fir the constructors */
    @Test
    @DisplayName("Test: Default name violates JavaDoc requirement") // Before it was just ""
    public void testDefaultNameDefect() {
        Recipe newRecipe = new Recipe();
        assertTrue(newRecipe.getName().trim().length() > 0, 
                "Must have a char for default Name");
    }

    @Test
    @DisplayName("Test: Default constructor initializes all fields")
    public void testDefaultConstructor() {
        assertAll("Default constructor values",
            () -> assertTrue(recipe.getName().trim().length() > 0, "Name should not be empty"),
            () -> assertEquals(0, recipe.getPrice()),
            () -> assertEquals(0, recipe.getAmtCoffee()),
            () -> assertEquals(0, recipe.getAmtMilk()),
            () -> assertEquals(0, recipe.getAmtSugar()),
            () -> assertEquals(0, recipe.getAmtChocolate())
        );
    }

    @Test
    @DisplayName("Test: Make a valid state")
    public void testValidState() {
        assertNotNull(recipe.getName());
        assertTrue(recipe.getPrice() >= 0);
        assertTrue(recipe.getAmtCoffee() >= 0);
    }
////////////////////////////////////////////////////////////////////////////////
    //** Coffee Content Tests */
     @Test
    @DisplayName("Test: Valid coffee amount")
    public void testValidCoffeeAmount() {
        assertDoesNotThrow(() -> {
            recipe.setAmtCoffee("2");
            assertEquals(3, recipe.getAmtCoffee());
        });
    }

    @Test
    @DisplayName("Test: user input ")
    public void testZeroCoffeeAmount() {
        assertDoesNotThrow(() -> {
            recipe.setAmtCoffee("0");
            assertEquals(0, recipe.getAmtCoffee());
        });
    }

    @Test
    @DisplayName("Test: Negative coffee amount")
    public void testNegativeCoffeeAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("-1");
        }, "Negative coffee amount should throw exception");
    }

    @Test
    @DisplayName("Test: Invalid coffee amount string")
    public void testInvalidCoffeeAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("abc");
        }, "Coffee amount can not be left 0 or blank");
    }

    @Test
    @DisplayName("Test: Empty coffee amount string")
    public void testEmptyCoffeeAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("");
        }, "Coffee amount can not be left 0 or blank");
    }

}
