import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NutritionistTest {

    private Nutritionist nutritionist;
    private FoodDatabase database;
    private static final String USERNAME = "nutri";
    private static final String PASSWORD = "secret";

    @BeforeEach
    void setUp() {
        nutritionist = new Nutritionist("Dr.", "Wellness", 60.0, USERNAME, PASSWORD);
        database = new FoodDatabase();
    }

    @Test
    void shouldAuthenticateNutritionistWithCorrectCredentials() {
        // Act
        boolean isAuthenticated = nutritionist.authenticate(USERNAME, PASSWORD);

        // Assert
        assertTrue(isAuthenticated);
    }

    @Test
    void shouldFailAuthenticationWithBadCredentials() {
        // Act
        boolean isAuthenticated = nutritionist.authenticate(USERNAME, "wrong");

        // Assert
        assertFalse(isAuthenticated);
    }

    @Test
    void shouldAddFoodToDatabase() {
        // Arrange
        String foodName = "Spinach";
        double calories = 23.0;

        // Act
        nutritionist.addFoodToDatabase(database, foodName, calories);

        // Assert
        assertTrue(database.foodExists(foodName), "Nutritionist should be able to add food.");
        assertEquals(calories, database.getCalories(foodName), 0.001);
        assertEquals(1, database.getTotalFoods());
    }

    @Test
    void shouldUpdateFoodInDatabase() {
        // Arrange
        database.addFood("Taco", 150.0);
        double newCalories = 200.0;

        // Act
        nutritionist.addFoodToDatabase(database, "Taco", newCalories); // Update existing

        // Assert
        assertEquals(1, database.getTotalFoods(), "Update should not increase food count.");
        assertEquals(newCalories, database.getCalories("Taco"), 0.001);
    }
}