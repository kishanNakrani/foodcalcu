import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;
    private static final String DEFAULT_USERNAME = "testuser";
    private static final String DEFAULT_PASSWORD = "testpassword";

    // Set up a standard user before each test
    @BeforeEach
    void setUp() {
        user = new User("Jane", "Smith", 65.0, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    @Test
    void shouldAuthenticateWithCorrectCredentials() {
        // Act
        boolean isAuthenticated = user.authenticate(DEFAULT_USERNAME, DEFAULT_PASSWORD);

        // Assert
        assertTrue(isAuthenticated, "User should be authenticated with correct username and password.");
    }

    @Test
    void shouldFailAuthenticationWithIncorrectPassword() {
        // Act
        boolean isAuthenticated = user.authenticate(DEFAULT_USERNAME, "wrongpass");

        // Assert
        assertFalse(isAuthenticated, "User should not be authenticated with incorrect password.");
    }

    @Test
    void shouldCorrectlyCalculateFullName() {
        // Act
        String fullName = user.getFullName();

        // Assert
        assertEquals("Jane Smith", fullName, "Full name should be the concatenation of first and last names.");
    }

    @Test
    void shouldIncreaseCalorieIntakeWhenAddingCalories() {
        // Arrange
        double initialCalories = user.getCalorieIntake(); // Should be 0.0
        double caloriesToAdd = 250.5;

        // Act
        user.addCalories(caloriesToAdd);

        // Assert
        assertEquals(initialCalories + caloriesToAdd, user.getCalorieIntake(), 0.001, "Calorie intake should be updated correctly.");
    }

    @Test
    void shouldResetDailyCalorieIntakeToZero() {
        // Arrange
        user.addCalories(500.0);
        assertTrue(user.getCalorieIntake() > 0);

        // Act
        user.resetDailyCalories();

        // Assert
        assertEquals(0.0, user.getCalorieIntake(), 0.001, "Calorie intake should be reset to 0.0.");
    }

    @Test
    void shouldUpdateFirstNameAndWeight() {
        // Arrange
        String newName = "Janet";
        double newWeight = 67.5;

        // Act
        user.setFirstName(newName);
        user.setWeight(newWeight);

        // Assert
        assertEquals(newName, user.getFirstName());
        assertEquals(newWeight, user.getWeight(), 0.001);
    }
}