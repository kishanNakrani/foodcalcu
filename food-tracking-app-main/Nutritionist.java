public class Nutritionist {
    // Basic profile information
    private String firstName;
    private String lastName;
    private double weight; // in kg

    // Authentication
    private String username;
    private String password;

    // Default sample nutritionist credentials
    public static final String DEFAULT_USERNAME = "nutritionist";
    public static final String DEFAULT_PASSWORD = "nutri123";

    // Constructor
    public Nutritionist(String firstName, String lastName, double weight, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
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

    // Authentication methods
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Nutritionist-specific methods (to be implemented)
    public void checkUserCalories(User user) {
        System.out.println("Checking calories for user: " + user.getFullName());
        System.out.println("Current calorie intake: " + user.getCalorieIntake());
    }

    public void addFoodToDatabase(FoodDatabase database, String foodName, double calories) {
        database.addFood(foodName, calories);
        System.out.println("Added food: " + foodName + " (" + calories + " calories)");
    }

    // Utility method
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Nutritionist{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", weight=" + weight +
                ", username='" + username + '\'' +
                '}';
    }
}
