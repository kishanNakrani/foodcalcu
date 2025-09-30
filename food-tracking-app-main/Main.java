import java.util.Scanner;

public class Main {
    private static FoodDatabase foodDB = new FoodDatabase();
    private static Scanner scanner = new Scanner(System.in);

    // Pre-created users for demo (normally would be created through registration)
    private static User currentUser = new User("John", "Doe", 70.0, "john", "password");
    private static Admin currentAdmin = new Admin("Admin", "User", 80.0, "admin", "admin123");
    private static Nutritionist currentNutritionist = new Nutritionist("Dr.", "Healthy", 65.0, "nutritionist", "nutri123");

    public static void main(String[] args) {
        // Check if user wants interactive mode
        if (args.length > 0 && args[0].equals("interactive")) {
            runInteractiveMode();
            return;
        }

        // Default: Demo mode
        initializeSampleFoods();

        System.out.println("🍎 Food Tracking App - Demo Mode!");
        System.out.println("==================================");

        // Demo all functionality
        demoUserFeatures();
        demoNutritionistFeatures();
        demoAdminFeatures();

        System.out.println("\n🎉 Demo completed! The app is working!");
        System.out.println("💡 To run interactively: java Main interactive");
    }

    private static void runInteractiveMode() {
        System.out.println("🍎 Welcome to Food Tracking App - Interactive Mode!");
        System.out.println("==================================================");

        while (true) {
            showMainMenu();
            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    showSignUpPlaceholder();
                    break;
                case 2:
                    handleSignIn();
                    break;
                case 3:
                    System.out.println("👋 Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void showSignUpPlaceholder() {
        System.out.println("\n📝 Sign Up (Demo)");
        System.out.println("---------------------");
        System.out.println("This is a testing build, so sign up is not implemented.");
        System.out.println("Use the demo accounts to sign in:");
        System.out.println("- User: " + currentUser.getUsername() + " / " + currentUser.getPassword());
        System.out.println("- Nutritionist: " + currentNutritionist.getUsername() + " / " + currentNutritionist.getPassword());
        System.out.println("- Admin: " + currentAdmin.getUsername() + " / " + currentAdmin.getPassword());
    }

    private static void handleSignIn() {
        while (true) {
            showRoleSelectionMenu();
            int roleChoice = getIntInput("Choose a role: ");

            switch (roleChoice) {
                case 1:
                    if (authenticateRole("User", currentUser::authenticate)) {
                        userMode();
                    }
                    break;
                case 2:
                    if (authenticateRole("Nutritionist", currentNutritionist::authenticate)) {
                        nutritionistMode();
                    }
                    break;
                case 3:
                    if (authenticateRole("Admin", currentAdmin::authenticate)) {
                        adminMode();
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void showRoleSelectionMenu() {
        System.out.println("\n🔐 Sign In");
        System.out.println("---------------------");
        System.out.println("1. 👤 User");
        System.out.println("2. 👩‍⚕️ Nutritionist");
        System.out.println("3. 👑 Admin");
        System.out.println("4. 🔙 Back");
    }

    private static boolean authenticateRole(String roleName, AuthenticationFunction authFunction) {
        System.out.println("\nAttempting to sign in as " + roleName);
        String username = getStringInput("Username: ");
        String password = getStringInput("Password: ");

        if (authFunction.authenticate(username, password)) {
            System.out.println("✅ Login successful as " + roleName + "!");
            return true;
        }

        System.out.println("❌ Incorrect credentials. Please try again.");
        return false;
    }

    private static void initializeSampleFoods() {
        foodDB.addFood("Apple", 95.0);
        foodDB.addFood("Banana", 105.0);
        foodDB.addFood("Chicken Breast", 165.0);
        foodDB.addFood("Brown Rice", 216.0);
        foodDB.addFood("Salmon", 206.0);
        foodDB.addFood("Broccoli", 55.0);
        System.out.println("✅ Sample foods loaded into database");
    }

    private static void demoUserFeatures() {
        System.out.println("\n👤 DEMO: User Features");
        System.out.println("======================");

        System.out.println("1. User searches for food:");
        currentUser.searchFood(foodDB, "Apple");

        System.out.println("\n2. User searches foods by name:");
        currentUser.searchFoodsByName(foodDB, "an");

        System.out.println("\n3. User adds calories:");
        System.out.println("Before: " + currentUser.getCalorieIntake() + " calories");
        currentUser.addCalories(95.0); // Apple
        currentUser.addCalories(105.0); // Banana
        System.out.println("After: " + currentUser.getCalorieIntake() + " calories");

        System.out.println("\n4. User resets daily calories:");
        currentUser.resetDailyCalories();
    }

    private static void demoNutritionistFeatures() {
        System.out.println("\n👩‍⚕️ DEMO: Nutritionist Features");
        System.out.println("================================");

        System.out.println("1. Nutritionist checks user calories:");
        currentNutritionist.checkUserCalories(currentUser);

        System.out.println("\n2. Nutritionist adds food to database:");
        currentNutritionist.addFoodToDatabase(foodDB, "Quinoa", 222.0);

        System.out.println("\n3. Database now has " + foodDB.getTotalFoods() + " foods");
    }

    private static void demoAdminFeatures() {
        System.out.println("\n👑 DEMO: Admin Features");
        System.out.println("=======================");

        System.out.println("1. Admin manages food database:");
        currentAdmin.manageFoodDatabase(foodDB, "add", "Tuna", 184.0);

        System.out.println("\n2. Admin views system statistics:");
        currentAdmin.viewSystemStats(foodDB);
    }

    private static void showMainMenu() {
        System.out.println("\n📋 Main Menu:");
        System.out.println("1. 📝 Sign Up (demo)");
        System.out.println("2. 🔐 Sign In");
        System.out.println("3. 🚪 Exit");
        System.out.println("===============================");
    }

    private static void userMode() {
        System.out.println("\n👤 USER MODE");
        System.out.println("Current User: " + currentUser.getFullName());

        while (true) {
            System.out.println("\n📋 User Menu:");
            System.out.println("1. 🔍 Search for a specific food");
            System.out.println("2. 🔎 Search foods by name");
            System.out.println("3. ➕ Add calories to intake");
            System.out.println("4. 📊 View my calorie intake");
            System.out.println("5. 🔄 Reset daily calories");
            System.out.println("6. 👤 View/Update profile");
            System.out.println("7. 🔙 Back to main menu");

            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter food name: ");
                    String foodName = scanner.nextLine();
                    currentUser.searchFood(foodDB, foodName);
                    break;
                case 2:
                    System.out.print("Enter search term: ");
                    String searchTerm = scanner.nextLine();
                    currentUser.searchFoodsByName(foodDB, searchTerm);
                    break;
                case 3:
                    System.out.print("Enter calories to add: ");
                    double calories = getDoubleInput("");
                    currentUser.addCalories(calories);
                    break;
                case 4:
                    System.out.println("📊 Current calorie intake: " + currentUser.getCalorieIntake() + " calories");
                    break;
                case 5:
                    currentUser.resetDailyCalories();
                    break;
                case 6:
                    showUserProfile();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void nutritionistMode() {
        System.out.println("\n👩‍⚕️ NUTRITIONIST MODE");
        System.out.println("Current Nutritionist: " + currentNutritionist.getFullName());

        while (true) {
            System.out.println("\n📋 Nutritionist Menu:");
            System.out.println("1. 👀 Check user calories");
            System.out.println("2. ➕ Add food to database");
            System.out.println("3. 📊 View all foods");
            System.out.println("4. 🔙 Back to main menu");

            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    currentNutritionist.checkUserCalories(currentUser);
                    break;
                case 2:
                    System.out.print("Enter food name: ");
                    String foodName = scanner.nextLine();
                    System.out.print("Enter calories: ");
                    double calories = getDoubleInput("");
                    currentNutritionist.addFoodToDatabase(foodDB, foodName, calories);
                    break;
                case 3:
                    System.out.println("📋 All foods in database:");
                    for (String food : foodDB.getAllFoods()) {
                        System.out.println("- " + food + ": " + foodDB.getCalories(food) + " calories");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void adminMode() {
        System.out.println("\n👑 ADMIN MODE");
        System.out.println("Current Admin: " + currentAdmin.getFullName());

        while (true) {
            System.out.println("\n📋 Admin Menu:");
            System.out.println("1. ➕ Add food to database");
            System.out.println("2. ➖ Remove food from database");
            System.out.println("3. 📊 View system statistics");
            System.out.println("4. 🔙 Back to main menu");

            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter food name: ");
                    String foodName = scanner.nextLine();
                    System.out.print("Enter calories: ");
                    double calories = getDoubleInput("");
                    currentAdmin.manageFoodDatabase(foodDB, "add", foodName, calories);
                    break;
                case 2:
                    System.out.print("Enter food name to remove: ");
                    String removeFood = scanner.nextLine();
                    currentAdmin.manageFoodDatabase(foodDB, "remove", removeFood, 0);
                    break;
                case 3:
                    currentAdmin.viewSystemStats(foodDB);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void showUserProfile() {
        System.out.println("\n👤 User Profile:");
        System.out.println("Name: " + currentUser.getFullName());
        System.out.println("Weight: " + currentUser.getWeight() + " kg");
        System.out.println("Calorie Intake: " + currentUser.getCalorieIntake() + " calories");

        System.out.println("\nUpdate profile? (y/n): ");
        String update = scanner.nextLine().toLowerCase();

        if (update.equals("y") || update.equals("yes")) {
            System.out.print("New first name: ");
            String firstName = scanner.nextLine();
            if (!firstName.trim().isEmpty()) {
                currentUser.setFirstName(firstName);
            }

            System.out.print("New last name: ");
            String lastName = scanner.nextLine();
            if (!lastName.trim().isEmpty()) {
                currentUser.setLastName(lastName);
            }

            System.out.print("New weight (kg): ");
            double weight = getDoubleInput("");
            if (weight > 0) {
                currentUser.setWeight(weight);
            }

            System.out.println("✅ Profile updated!");
        }
    }

    // Helper methods for input
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return input;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("❌ Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return input;
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @FunctionalInterface
    private interface AuthenticationFunction {
        boolean authenticate(String username, String password);
    }
}
