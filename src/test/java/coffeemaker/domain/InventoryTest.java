package coffeemaker.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import coffeemaker.exceptions.InventoryException;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;
    private Recipe recipe;
    private Recipe smallRecipe;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        recipe = new Recipe(); // Create a simple recipe for testing

        try {
            recipe.setName("Coffee Delight");
            recipe.setAmtCoffee("5");
            recipe.setAmtMilk("2");
            recipe.setAmtSugar("3");
            recipe.setAmtChocolate("1");
            recipe.setPrice("50");
        } catch (Exception e) {
            fail("Failed to set up recipe: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Constructor should initialize all ingredients to 15")
    void testConstructorInitializesInventory() {
        assertAll("Inventory should be initialized with 15 units of each ingredient",
            () -> assertEquals(15, inventory.getCoffee(), "Coffee should be 15"),
            () -> assertEquals(15, inventory.getMilk(), "Milk should be 15"),
            () -> assertEquals(15, inventory.getSugar(), "Sugar should be 15"),
            () -> assertEquals(15, inventory.getChocolate(), "Chocolate should be 15")
        );
    }

    @Test
    @DisplayName("addSugar - Should throw InventoryException for negative string input")
    void testAddSugarThrowsExceptionForNegativeStringInput() {
        int initialSugar = inventory.getSugar();

        assertThrows(
            InventoryException.class,
            () -> inventory.addSugar("-5"),
            "addSugar should throw InventoryException when adding a negative amount"
        );
        assertEquals(initialSugar, inventory.getSugar(), "Sugar amount should not change if invalid input is provided.");
    }
    
    @Test
    @DisplayName("setCoffee - Should throw IllegalArgumentException for negative input")
    void testSetCoffeeThrowsExceptionForNegativeInput() {
        int initialMilk = inventory.getMilk();
        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.setCoffee(-10),
            "setCoffee should throw IllegalArgumentException for negative input.");
        assertEquals(initialMilk, inventory.getMilk(), "Coffee amount should not change if invalid input is provided.");
    }
    
    @Test
    @DisplayName("setMilk - Should throw IllegalArgumentException for negative input")
    void testSetMilkThrowsExceptionForNegativeInput() {
        int initialMilk = inventory.getMilk();
        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.setMilk(-10),
            "setMilk should throw IllegalArgumentException for negative input.");
        assertEquals(initialMilk, inventory.getMilk(), "Milk amount should not change if invalid input is provided.");
    }
    
    @Test
    @DisplayName("setSugar - Should throw IllegalArgumentException for negative input")
    void testSetSugarThrowsExceptionForNegativeInput() {
        int initialMilk = inventory.getMilk();
        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.setSugar(-10),
            "setSugar should throw IllegalArgumentException for negative input.");
        assertEquals(initialMilk, inventory.getMilk(), "Sugar amount should not change if invalid input is provided.");
    }
    
    @Test
    @DisplayName("setChocolate - Should throw IllegalArgumentException for negative input")
    void testSetChocolateThrowsExceptionForNegativeInput() {
        int initialMilk = inventory.getMilk();
        assertThrows(
            IllegalArgumentException.class,
            () -> inventory.setChocolate(-10),
            "setChocolate should throw IllegalArgumentException for negative input.");
        assertEquals(initialMilk, inventory.getMilk(), "Chocolare amount should not change if invalid input is provided.");
    }
    
    @Test
    @DisplayName("useIngredients - Should correctly subtract coffee from inventory")
    void testUseIngredientsSubtractsCoffee() {
        int initialCoffee = inventory.getCoffee();
        int recipeCoffee = recipe.getAmtCoffee();

        assertTrue(inventory.enoughIngredients(recipe), "Should have enough ingredients initially.");

        inventory.useIngredients(recipe);

        assertEquals(
            (initialCoffee - recipeCoffee),
            inventory.getCoffee(),
            "Coffee quantity should be correctly decremented after use."
        );
    }
}
