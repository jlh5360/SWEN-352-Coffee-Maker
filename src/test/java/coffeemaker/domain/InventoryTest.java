package coffeemaker.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import coffeemaker.exceptions.InventoryException;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;
    private Recipe recipe;
    private Recipe insufficientCoffeeRecipe;
    private Recipe insufficientMilkRecipe;
    private Recipe insufficientSugarRecipe;
    private Recipe insufficientChocolateRecipe;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        recipe = new Recipe();   //Create a simple recipe for testing

        try {
            recipe.setName("Coffee Delight");
            recipe.setAmtCoffee("5");
            recipe.setAmtMilk("2");
            recipe.setAmtSugar("3");
            recipe.setAmtChocolate("1");
            recipe.setPrice("50");
            
            insufficientCoffeeRecipe = new Recipe();
            insufficientCoffeeRecipe.setAmtCoffee("16");   //Inventory has 15
            insufficientCoffeeRecipe.setAmtMilk("1");
            insufficientCoffeeRecipe.setAmtSugar("1");
            insufficientCoffeeRecipe.setAmtChocolate("1");
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
    @DisplayName("addCoffee - Should add coffee correctly with valid positive string input")
    void testAddCoffeeWithValidPositiveString() {
        int initialCoffee = inventory.getCoffee();
        inventory.addCoffee("10");
        
        assertEquals(initialCoffee + 10, inventory.getCoffee(), "Coffee should increase by 10.");
    }

    @Test
    @DisplayName("addCoffee - Should throw InventoryException for non-numeric string input")
    void testAddCoffeeThrowsExceptionForNonNumericInput() {
        int initialCoffee = inventory.getCoffee();
        
        assertThrows(
            InventoryException.class,
            () -> inventory.addCoffee("invalid"),
            "addCoffee should throw InventoryException for non-numeric input"
        );
        assertEquals(initialCoffee, inventory.getCoffee(), "Coffee amount should not change if invalid input is provided.");
    }

    @Test
    @DisplayName("addCoffee - Should throw InventoryException for negative string input")
    void testAddCoffeeThrowsExceptionForNegativeStringInput() {
        int initialCoffee = inventory.getCoffee();

        assertThrows(
            InventoryException.class,
            () -> inventory.addCoffee("-5"),
            "addCoffee should throw InventoryException for negative amount"
        );
        assertEquals(initialCoffee, inventory.getCoffee(), "Coffee amount should not change if invalid input is provided.");
    }

    @Test
    @DisplayName("addMilk - Should add milk correctly with valid positive string input")
    void testAddMilkWithValidPositiveString() {
        int initialMilk = inventory.getMilk();
        inventory.addMilk("8");

        assertEquals(initialMilk + 8, inventory.getMilk(), "Milk should increase by 8.");
    }

    @Test
    @DisplayName("addMilk - Should throw InventoryException for non-numeric string input")
    void testAddMilkThrowsExceptionForNonNumericInput() {
        int initialMilk = inventory.getMilk();

        assertThrows(
            InventoryException.class,
            () -> inventory.addMilk("xyz"),
            "addMilk should throw InventoryException for non-numeric input"
        );
        assertEquals(initialMilk, inventory.getMilk(), "Milk amount should not change if invalid input is provided.");
    }

    @Test
    @DisplayName("addMilk - Should throw InventoryException for negative string input")
    void testAddMilkThrowsExceptionForNegativeStringInput() {
        int initialMilk = inventory.getMilk();

        assertThrows(
            InventoryException.class,
            () -> inventory.addMilk("-3"),
            "addMilk should throw InventoryException for negative amount"
        );
        assertEquals(initialMilk, inventory.getMilk(), "Milk amount should not change if invalid input is provided.");
    }

    @Test
    @DisplayName("addSugar - Should add sugar correctly with valid positive string input")
    void testAddSugarWithValidPositiveString() {
        int initialSugar = inventory.getSugar();
        inventory.addSugar("7");

        assertEquals(initialSugar + 7, inventory.getSugar(), "Sugar should increase by 7.");
    }

    @Test
    @DisplayName("addSugar - Should throw InventoryException with a specific message for non-numeric input")
    void testAddSugarThrowsSpecificExceptionForNonNumericInput() {
        InventoryException thrown = assertThrows(
            InventoryException.class,
            () -> inventory.addSugar("abc"),
            "addSugar should throw InventoryException for non-numeric input"
        );

        assertEquals("Units of sugar must be a positive integer", thrown.getMessage(),
            "Error message for non-numeric input should be specific.");
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
    @DisplayName("addChocolate - Should add chocolate correctly with valid positive string input")
    void testAddChocolateWithValidPositiveString() {
        int initialChocolate = inventory.getChocolate();
        inventory.addChocolate("12");
        assertEquals(initialChocolate + 12, inventory.getChocolate(), "Chocolate should increase by 12.");
    }

    @Test
    @DisplayName("addChocolate - Should throw InventoryException for non-numeric string input")
    void testAddChocolateThrowsExceptionForNonNumericInput() {
        int initialChocolate = inventory.getChocolate();
        assertThrows(
            InventoryException.class,
            () -> inventory.addChocolate("badvalue"),
            "addChocolate should throw InventoryException for non-numeric input"
        );
        assertEquals(initialChocolate, inventory.getChocolate(), "Chocolate amount should not change if invalid input is provided.");
    }

    @Test
    @DisplayName("addChocolate - Should throw InventoryException for negative string input")
    void testAddChocolateThrowsExceptionForNegativeStringInput() {
        int initialChocolate = inventory.getChocolate();
        assertThrows(
            InventoryException.class,
            () -> inventory.addChocolate("-1"),
            "addChocolate should throw InventoryException for negative amount"
        );
        assertEquals(initialChocolate, inventory.getChocolate(), "Chocolate amount should not change if invalid input is provided.");
    }

    @Test
    @DisplayName("setCoffee - Should set coffee correctly to a valid positive amount")
    void testSetCoffeeValidPositive() {
        inventory.setCoffee(25);

        assertEquals(25, inventory.getCoffee(), "Coffee should be set to 25.");
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
    @DisplayName("setMilk - Should set milk correctly to a valid positive amount")
    void testSetMilkValidPositive() {
        inventory.setMilk(30);

        assertEquals(30, inventory.getMilk(), "Milk should be set to 30.");
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
    @DisplayName("setSugar - Should set sugar correctly to a valid positive amount")
    void testSetSugarValidPositive() {
        inventory.setSugar(40);

        assertEquals(40, inventory.getSugar(), "Sugar should be set to 40.");
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
    @DisplayName("setChocolate - Should set chocolate correctly to a valid positive amount")
    void testSetChocolateValidPositive() {
        inventory.setChocolate(35);

        assertEquals(35, inventory.getChocolate(), "Chocolate should be set to 35.");
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
    @DisplayName("enoughIngredients - Should return true when all ingredients are sufficient")
    void testEnoughIngredientsTrueWhenAllSufficient() {
        assertTrue(inventory.enoughIngredients(recipe),
            "enoughIngredients should return true if all ingredients are sufficient.");
    }

    @Test
    @DisplayName("enoughIngredients - Should return false when coffee is insufficient")
    void testEnoughIngredientsFalseWhenCoffeeInsufficient() {
        assertFalse(inventory.enoughIngredients(insufficientCoffeeRecipe),
            "enoughIngredients should return false if coffee is insufficient.");
    }

    @Test
    @DisplayName("enoughIngredients - Should return false when milk is insufficient")
    void testEnoughIngredientsFalseWhenMilkInsufficient() {
        insufficientMilkRecipe = new Recipe();
        insufficientMilkRecipe.setAmtCoffee("1");
        insufficientMilkRecipe.setAmtMilk("16");   //Inventory has 15
        insufficientMilkRecipe.setAmtSugar("1");
        insufficientMilkRecipe.setAmtChocolate("1");

        assertFalse(inventory.enoughIngredients(insufficientMilkRecipe),
            "enoughIngredients should return false if milk is insufficient.");
    }

    @Test
    @DisplayName("enoughIngredients - Should return false when sugar is insufficient")
    void testEnoughIngredientsFalseWhenSugarInsufficient() {
        insufficientSugarRecipe = new Recipe();
        insufficientSugarRecipe.setAmtCoffee("1");
        insufficientSugarRecipe.setAmtMilk("1");
        insufficientSugarRecipe.setAmtSugar("16"); // Inventory has 15
        insufficientSugarRecipe.setAmtChocolate("1");

        assertFalse(inventory.enoughIngredients(insufficientSugarRecipe),
            "enoughIngredients should return false if sugar is insufficient.");
    }

    @Test
    @DisplayName("enoughIngredients - Should return false when chocolate is insufficient")
    void testEnoughIngredientsFalseWhenChocolateInsufficient() {
        insufficientChocolateRecipe = new Recipe();
        insufficientChocolateRecipe.setAmtCoffee("1");
        insufficientChocolateRecipe.setAmtMilk("1");
        insufficientChocolateRecipe.setAmtSugar("1");
        insufficientChocolateRecipe.setAmtChocolate("16");   //Inventory has 15
        
        assertFalse(inventory.enoughIngredients(insufficientChocolateRecipe),
            "enoughIngredients should return false if chocolate is insufficient.");
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

    @Test
    @DisplayName("useIngredients - Should correctly subtract all ingredients when exact amounts are available")
    void testUseIngredientsExactAmounts() {
        inventory.setCoffee(5);
        inventory.setMilk(2);
        inventory.setSugar(3);
        inventory.setChocolate(1);

        assertTrue(inventory.useIngredients(recipe), "Should be able to use ingredients when exact amounts are available.");
        assertAll("Ingredients should be zero after exact use",
            () -> assertEquals(0, inventory.getCoffee(), "Coffee should be 0."),
            () -> assertEquals(0, inventory.getMilk(), "Milk should be 0."),
            () -> assertEquals(0, inventory.getSugar(), "Sugar should be 0."),
            () -> assertEquals(0, inventory.getChocolate(), "Chocolate should be 0.")
        );
    }

    @Test
    @DisplayName("useIngredients - Should correctly subtract all ingredients when sufficient amounts are available")
    void testUseIngredientsSufficientAmounts() {
        inventory.setCoffee(40);
        inventory.setMilk(28);
        inventory.setSugar(50);
        inventory.setChocolate(16);

        int initialCoffee = inventory.getCoffee();
        int initialMilk = inventory.getMilk();
        int initialSugar = inventory.getSugar();
        int initialChocolate = inventory.getChocolate();

        assertTrue(inventory.useIngredients(recipe), "Should be able to use ingredients when sufficient amounts are available.");

        assertAll("All ingredients should be correctly decremented after use with sufficient amounts",
            () -> assertEquals(initialCoffee - recipe.getAmtCoffee(), inventory.getCoffee(), "Coffee should be correctly decremented."),
            () -> assertEquals(initialMilk - recipe.getAmtMilk(), inventory.getMilk(), "Milk should be correctly decremented."),
            () -> assertEquals(initialSugar - recipe.getAmtSugar(), inventory.getSugar(), "Sugar should be correctly decremented."),
            () -> assertEquals(initialChocolate - recipe.getAmtChocolate(), inventory.getChocolate(), "Chocolate should be correctly decremented.")
        );
    }
    
    @Test
    @DisplayName("useIngredients - Should return false when ingredients are insufficient")
    void testUseIngredientsReturnsFalseWhenInsufficient() {
        assertFalse(inventory.useIngredients(insufficientCoffeeRecipe),
            "useIngredients should return false when ingredients are insufficient.");
    }
    
    @Test
    @DisplayName("toString - Should return a correctly formatted string of inventory contents")
    void testToString() {
        String expectedString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
        assertEquals(expectedString, inventory.toString(), "toString should return the correct inventory representation.");

        inventory.setCoffee(20);
        inventory.setMilk(10);
        expectedString = "Coffee: 20\nMilk: 10\nSugar: 15\nChocolate: 15\n";
        
        assertEquals(expectedString, inventory.toString(), "toString should reflect updated inventory.");
    }
}
