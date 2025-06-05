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


}
