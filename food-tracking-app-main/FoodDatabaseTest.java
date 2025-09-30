import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FoodDatabaseTest {

    private FoodDatabase database;

    // This method runs before every test to ensure a clean slate.
    @BeforeEach
    void setUp() {
        database = new FoodDatabase();
        database.addFood("Apple", 95.0);
        database.addFood("Banana", 105.0);
    }

    @Test
    void shouldAddFoodAndIncreaseTotalCount() {
        // Arrange
        String newFood = "Orange";

        // Act
        database.addFood(newFood, 62.0);

        // Assert
        assertTrue(database.foodExists(newFood), "New food should exist in the database.");
        assertEquals(3, database.getTotalFoods(), "Total food count should be 3.");
        assertEquals(62.0, database.getCalories(newFood), 0.001, "Calories for the new food should be correct.");
    }

    @Test
    void shouldUpdateExistingFoodCalories() {
        // Arrange
        String existingFood = "Apple";
        double newCalories = 100.0;

        // Act
        database.addFood(existingFood, newCalories); // Should update, not duplicate

        // Assert
        assertEquals(2, database.getTotalFoods(), "Total food count should remain 2 after update.");
        assertEquals(newCalories, database.getCalories(existingFood), 0.001, "Calories should be updated.");
    }

    @Test
    void shouldRemoveFoodAndDecreaseTotalCount() {
        // Arrange
        String foodToRemove = "Banana";

        // Act
        database.removeFood(foodToRemove);

        // Assert
        assertFalse(database.foodExists(foodToRemove), "Food should no longer exist after removal.");
        assertEquals(1, database.getTotalFoods(), "Total food count should be 1.");
    }

    @Test
    void shouldReturnZeroForNonExistingFoodCalories() {
        // Act
        double calories = database.getCalories("NonExistentFood");

        // Assert
        assertEquals(0.0, calories, 0.001, "Should return 0.0 calories for a food not found.");
    }

    @Test
    void shouldReturnAllFoodNames() {
        // Act
        ArrayList<String> allFoods = database.getAllFoods();

        // Assert
        assertTrue(allFoods.contains("Apple"));
        assertTrue(allFoods.contains("Banana"));
        assertEquals(2, allFoods.size());
    }
}