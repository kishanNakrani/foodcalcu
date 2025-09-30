public class Admin {
    // Basic profile information
    private String firstName;
    private String lastName;
    private double weight; // in kg

    // Authentication
    private String username;
    private String password;

    // Default sample admin credentials
    public static final String DEFAULT_USERNAME = "admin";
    public static final String DEFAULT_PASSWORD = "admin123";

    // Constructor
    public Admin(String firstName, String lastName, double weight, String username, String password) {
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

    // Admin-specific methods - simplified without UserDatabase
    public void manageFoodDatabase(FoodDatabase foodDB, String action, String foodName, double calories) {
        switch (action.toLowerCase()) {
            case "add":
                foodDB.addFood(foodName, calories);
                System.out.println("Admin added food: " + foodName + " (" + calories + " calories)");
                break;
            case "remove":
                foodDB.removeFood(foodName);
                System.out.println("Admin removed food: " + foodName);
                break;
            default:
                System.out.println("Invalid action. Use 'add' or 'remove'");
        }
    }

    public void viewSystemStats(FoodDatabase foodDB) {
        System.out.println("System Statistics:");
        System.out.println("- Total foods in database: " + foodDB.getTotalFoods());
        System.out.println("- Available foods: " + foodDB.getAllFoods());
    }

    // Utility method
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", weight=" + weight +
                ", username='" + username + '\'' +
                '}';
    }
}
