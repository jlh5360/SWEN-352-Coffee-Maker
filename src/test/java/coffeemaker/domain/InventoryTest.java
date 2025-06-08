package coffeemaker.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import coffeemaker.exceptions.InventoryException;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
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
}
