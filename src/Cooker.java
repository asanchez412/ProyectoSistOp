import java.util.ArrayList;

public class Cooker {
    private Integer maxMeals;
    private Integer currentMeals = 0;
    private Meal.FoodType area;
    private ArrayList<Meal> toCook = new ArrayList<Meal>();
    private ArrayList<Meal> cooking = new ArrayList<Meal>();

    public Cooker(Integer maxSimultaneousMeals, Meal.FoodType area) {
        this.area = area;
        this.maxMeals = maxSimultaneousMeals;
    }

    public Meal.FoodType getArea() {
        return area;
    }

    public void enqueueMeals() {
        while(toCook.size() > 0) {
            if (currentMeals <= maxMeals) {
                Meal meal = toCook.get(0);
                cooking.add(meal);
                toCook.remove(0);
                currentMeals += 1;
            }
            else {
                break;
            }
        }
    }
    
    public void cook() {
        for (Meal meal : cooking) {
            meal.cook();
        }
    }

    public void addMeal(Meal meal){
        toCook.add(meal);
    }

    public Integer getCurrentMeals() {
        return this.currentMeals;
    }
}