import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    private Admin admin;
    private FoodDatabase database;
    private static final String USERNAME = "superuser";
    private static final String PASSWORD = "root";

    @BeforeEach
    void setUp() {
        admin = new Admin("System", "Boss", 90.0, USERNAME, PASSWORD);
        database = new FoodDatabase();
        database.addFood("Milk", 120.0);
    }

    @Test
    void shouldAuthenticateAdminWithCorrectCredentials() {
        // Act
        boolean isAuthenticated = admin.authenticate(USERNAME, PASSWORD);

        // Assert
        assertTrue(isAuthenticated);
    }

    @Test
    void adminShouldBeAbleToAddNewFood() {
        // Arrange
        String foodName = "Pizza";
        double calories = 300.0;

        // Act
        admin.manageFoodDatabase(database, "add", foodName, calories);

        // Assert
        assertTrue(database.foodExists(foodName), "Admin 'add' action should add food.");
        assertEquals(2, database.getTotalFoods());
    }

    @Test
    void adminShouldBeAbleToRemoveExistingFood() {
        // Arrange
        String foodName = "Milk";
        assertTrue(database.foodExists(foodName));

        // Act
        admin.manageFoodDatabase(database, "remove", foodName, 0.0);

        // Assert
        assertFalse(database.foodExists(foodName), "Admin 'remove' action should remove food.");
        assertEquals(0, database.getTotalFoods());
    }

    @Test
    void adminShouldBeAbleToUpdateFood() {
        // Arrange
        String foodName = "Milk";
        double newCalories = 150.0;

        // Act
        admin.manageFoodDatabase(database, "add", foodName, newCalories); // 'add' action updates if exists

        // Assert
        assertEquals(1, database.getTotalFoods(), "Update should not change food count.");
        assertEquals(newCalories, database.getCalories(foodName), 0.001);
    }

    @Test
    void shouldHandleInvalidActionGracefully() {
        // Arrange: The manageFoodDatabase method prints a message for invalid action.
        // In a proper test environment, we would capture System.out, but here we just ensure 
        // the database state remains unchanged.
        int initialCount = database.getTotalFoods();

        // Act
        admin.manageFoodDatabase(database, "INVALID_ACTION", "Carrot", 50.0);

        // Assert
        assertEquals(initialCount, database.getTotalFoods(), "Invalid action should not change database count.");
        assertFalse(database.foodExists("Carrot"), "Invalid action should not add food.");
    }
}