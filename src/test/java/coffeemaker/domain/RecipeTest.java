package coffeemaker.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import coffeemaker.exceptions.RecipeException;

public class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe();
    }

    //** Constructor */
    @Test
    @DisplayName("Test: Default constructor initializes all fields correctly")
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
    @DisplayName("Test: Default name meets JavaDoc requirement (non-empty)")
    public void testDefaultNameDefect() {
        Recipe newRecipe = new Recipe();
        assertTrue(newRecipe.getName().trim().length() > 0, 
                "Default name must have at least one character");
    }

    //** Name Setter */
    @Test
    @DisplayName("Test: Valid name can be set")
    public void testValidNameWorks() throws RecipeException {
        recipe.setName("Espresso");
        assertEquals("Espresso", recipe.getName());
    }

    @Test
    @DisplayName("Test: Null name throws exception")
    public void testNullNameThrowsException() {
        assertThrows(RecipeException.class, () -> {
            recipe.setName(null);
        }, "Null name should throw exception");
    }

    @Test
    @DisplayName("Test: Empty string name throws exception")
    public void testEmptyStringNameThrowsException() {
        assertThrows(RecipeException.class, () -> {
            recipe.setName("");
        }, "Empty string name should throw exception");
    }

    @Test
    @DisplayName("Test: Whitespace-only name throws exception")
    public void testWhitespaceNameThrowsException() {
        assertThrows(RecipeException.class, () -> {
            recipe.setName("   ");
        }, "Whitespace-only name should throw exception");
    }

    //** Price */
    @Test
    @DisplayName("Test: Valid price can be set")
    public void testValidPrice() throws RecipeException {
        recipe.setPrice("10");
        assertEquals(10, recipe.getPrice());
    }

    @Test
    @DisplayName("Test: Zero price is valid")
    public void testZeroPriceIsValid() throws RecipeException {
        recipe.setPrice("0");
        assertEquals(0, recipe.getPrice());
    }

    @Test
    @DisplayName("Test: Null price throws exception")
    public void testNullPrice() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice(null);
        }, "Null price should throw exception");
    }

    @Test
    @DisplayName("Test: Negative price throws exception")
    public void testNegativePrice() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("-1");
        }, "Negative price should throw exception");
    }

    @Test
    @DisplayName("Test: Invalid price characters throw exception")
    public void testPriceInvalidChars() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("5abc");
        }, "Invalid price characters should throw exception");
    }

    //** Coffee */
    @Test
    @DisplayName("Test: Valid coffee amount can be set")
    public void testValidCoffeeAmount() throws RecipeException {
        recipe.setAmtCoffee("2");
        assertEquals(2, recipe.getAmtCoffee());
    }

    @Test
    @DisplayName("Test: Zero coffee amount is valid")
    public void testZeroCoffeeAmount() throws RecipeException {
        recipe.setAmtCoffee("0");
        assertEquals(0, recipe.getAmtCoffee());
    }

    @Test
    @DisplayName("Test: Negative coffee amount throws exception")
    public void testNegativeCoffeeAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("-1");
        }, "Negative coffee amount should throw exception");
    }

    @Test
    @DisplayName("Test: Empty coffee string throws exception")
    public void testEmptyCoffeeAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("");
        }, "Empty coffee string should throw exception");
    }

    //** Milk */
    @Test
    @DisplayName("Test: Valid milk amount can be set")
    public void testValidMilkAmount() throws RecipeException {
        recipe.setAmtMilk("2");
        assertEquals(2, recipe.getAmtMilk());
    }

    @Test
    @DisplayName("Test: Negative milk amount throws exception")
    public void testNegativeMilkAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtMilk("-1");
        }, "Negative milk amount should throw exception");
    }

    @Test
    @DisplayName("Test: Invalid milk string throws exception")
    public void testInvalidMilkString() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtMilk("xyz");
        }, "Invalid milk string should throw exception");
    }

    //** Sugar */
    @Test
    @DisplayName("Test: Valid sugar amount can be set")
    public void testValidSugarAmount() throws RecipeException {
        recipe.setAmtSugar("5");
        assertEquals(5, recipe.getAmtSugar());
    }

    @Test
    @DisplayName("Test: Zero sugar amount is valid")
    public void testZeroSugarAmount() throws RecipeException {
        recipe.setAmtSugar("0");
        assertEquals(0, recipe.getAmtSugar());
    }

    @Test
    @DisplayName("Test: Negative sugar amount throws exception")
    public void testNegativeSugarAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtSugar("-1");
        }, "Negative sugar amount should throw exception");
    }

    @Test
    @DisplayName("Test: Null sugar string throws exception")
    public void testNullSugarString() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtSugar(null);
        }, "Null sugar string should throw exception");
    }

    //** Equal */
    @Test
    @DisplayName("Test: Valid chocolate amount can be set")
    public void testValidChocolateAmount() throws RecipeException {
        recipe.setAmtChocolate("3");
        assertEquals(3, recipe.getAmtChocolate());
    }

    @Test
    @DisplayName("Test: Zero chocolate amount is valid")
    public void testZeroChocolateAmount() throws RecipeException {
        recipe.setAmtChocolate("0");
        assertEquals(0, recipe.getAmtChocolate());
    }

    @Test
    @DisplayName("Test: Negative chocolate amount throws exception")
    public void testNegativeChocolateAmount() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtChocolate("-1");
        }, "Negative chocolate amount should throw exception");
    }

    @Test
    @DisplayName("Test: Empty chocolate string throws exception")
    public void testEmptyChocolateString() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtChocolate("");
        }, "Empty chocolate string should throw exception");
    }

    //** Equals */
    @Test
    @DisplayName("Test: Equals returns true when comparing object to itself")
    public void testEqualsSameObjectReference() {
        assertTrue(recipe.equals(recipe));
    }

    @Test
    @DisplayName("Test: Equals returns true for recipes with same name")
    public void testEqualsSameName() throws RecipeException {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1.setName("Coffee");
        recipe2.setName("Coffee");
        assertTrue(recipe1.equals(recipe2));
    }

    @Test
    @DisplayName("Test: Equals returns false for different classes")
    public void testEqualsDifferentClass() {
        assertFalse(recipe.equals("not a recipe"));
    }

    @Test
    @DisplayName("Test: Equals returns false when compared to null")
    public void testEqualsNull() {
        assertFalse(recipe.equals(null));
    }

    @Test
    @DisplayName("Test: Equals when both names are null")
    public void testEqualsBothNamesNull() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        
        try {
            java.lang.reflect.Field nameField = Recipe.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(recipe1, null);
            nameField.set(recipe2, null);
            
            assertTrue(recipe1.equals(recipe2));
        } catch (Exception e) {
            fail("Could not test both null names case");
        }
    }


    @Test
    @DisplayName("Test: Equals returns false for different names")
    public void testEqualsDifferentNames() throws RecipeException {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1.setName("Coffee");
        recipe2.setName("Latte");
        assertFalse(recipe1.equals(recipe2));
    }

    @Test
    @DisplayName("Test: Equals when this.name is null but other.name is not null")
    public void testEqualsNullNameVsNonNullName() throws RecipeException {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        
        try {
            java.lang.reflect.Field nameField = Recipe.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(recipe1, null);
            recipe2.setName("Coffee");
            
            assertFalse(recipe1.equals(recipe2));
        } catch (Exception e) {
            fail("Could not test null vs non-null name case");
        }
    }

    //** Hashcode */
    @Test
    @DisplayName("Test: Test wirh valid name")
    public void testHashCodeWithNonNullName() throws RecipeException {
        recipe.setName("TestRecipe");
        int hashCode = recipe.hashCode();
        assertTrue(hashCode != 31);
    }

    @Test
    @DisplayName("Test: Testing hashcode with nonexistent name")
    public void testHashCodeWithNullName() {
        Recipe testRecipe = new Recipe();
        try {
            java.lang.reflect.Field nameField = Recipe.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(testRecipe, null);
            int hashCode = testRecipe.hashCode();
            assertEquals(31, hashCode);
        } catch (Exception e) {
            fail("Could not test due to invalid name");
        }
    }

    @Test
    @DisplayName("Test: Testing for hashcode same name")
    public void testHashCode() throws RecipeException {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1.setName("Coffee");
        recipe2.setName("Coffee");
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
    }

    //** To-stringl */
    @Test
    @DisplayName("Test: Testing if toString returns the right format")
    public void testToString() throws RecipeException {
        recipe.setName("Espresso");
        assertEquals("Recipe{Espresso}", recipe.toString());
    }

}