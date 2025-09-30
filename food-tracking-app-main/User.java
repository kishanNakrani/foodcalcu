public class User {
    // Basic profile information
    private String firstName;
    private String lastName;
    private double weight; // in kg
    private double calorieIntake; // daily calorie intake

    // Authentication
    private String username;
    private String password;

    // Default sample user credentials
    public static final String DEFAULT_USERNAME = "john";
    public static final String DEFAULT_PASSWORD = "password";

    // Constructor
    public User(String firstName, String lastName, double weight, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.calorieIntake = 0.0; // starts at 0
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getWeight() {
        return weight;
    }

    public double getCalorieIntake() {
        return calorieIntake;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters for editable profile fields
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCalorieIntake(double calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    // Authentication methods
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Food search methods - direct access to FoodDatabase
    public void searchFood(FoodDatabase database, String foodName) {
        if (database.foodExists(foodName)) {
            double calories = database.getCalories(foodName);
            System.out.println("Found: " + foodName + " - " + calories + " calories");
        } else {
            System.out.println("Food '" + foodName + "' not found in database");
        }
    }

    public void searchFoodsByName(FoodDatabase database, String searchTerm) {
        boolean found = false;
        for (String food : database.getAllFoods()) {
            if (food.toLowerCase().contains(searchTerm.toLowerCase())) {
                double calories = database.getCalories(food);
                System.out.println("- " + food + ": " + calories + " calories");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No foods found containing '" + searchTerm + "'");
        }
    }

    // Add calories to user's daily intake
    public void addCalories(double calories) {
        this.calorieIntake += calories;
        System.out.println("Added " + calories + " calories. Total intake: " + this.calorieIntake);
    }

    // Reset daily calorie intake
    public void resetDailyCalories() {
        this.calorieIntake = 0.0;
        System.out.println("Daily calories reset to 0");
    }

    // Utility method
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", weight=" + weight +
                ", calorieIntake=" + calorieIntake +
                ", username='" + username + '\'' +
                '}';
    }
}
