import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodDatabase {
    // Using ArrayList as dynamic array for foods
    private ArrayList<String> foodNames;
    private Map<String, Double> foodCalories; // food name -> calories

    public FoodDatabase() {
        this.foodNames = new ArrayList<>();
        this.foodCalories = new HashMap<>();
    }

    // Add food to database
    public void addFood(String foodName, double calories) {
        if (!foodNames.contains(foodName)) {
            foodNames.add(foodName);
            foodCalories.put(foodName, calories);
        } else {
            // Update existing food
            foodCalories.put(foodName, calories);
        }
    }

    // Remove food from database
    public void removeFood(String foodName) {
        foodNames.remove(foodName);
        foodCalories.remove(foodName);
    }

    // Get calories for a food
    public double getCalories(String foodName) {
        return foodCalories.getOrDefault(foodName, 0.0);
    }

    // Get all foods
    public ArrayList<String> getAllFoods() {
        return new ArrayList<>(foodNames);
    }

    // Check if food exists
    public boolean foodExists(String foodName) {
        return foodNames.contains(foodName);
    }

    // Get total number of foods
    public int getTotalFoods() {
        return foodNames.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Food Database:\n");
        for (String food : foodNames) {
            sb.append("- ").append(food).append(": ").append(foodCalories.get(food)).append(" calories\n");
        }
        return sb.toString();
    }
}
