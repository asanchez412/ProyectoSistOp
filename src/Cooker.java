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

    public void enqueueMeals(Integer i) {
        while(toCook.size() > 0) {
            if (currentMeals <= maxMeals) {
                Meal meal = toCook.get(0);
                cooking.add(meal);
                toCook.remove(0);
                currentMeals += 1;
                CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(meal.getOrder().getId()), meal.getFoodType().toString(), "Comenzó a prepararse", Integer.toString(meal.getOrder().getBusiness().getId()), "Asignado"});
            }
            else {
                break;
            }
        }
    }
    
    public void cook(Integer i) {
        for (Meal meal : cooking) {
            meal.cook();
        }
    }

    public void addMeal(Meal meal){
        toCook.add(meal);
    }

    public Integer getCurrentMeals() {
        return currentMeals;
    }

    public Integer getMaxMeals() {
        return maxMeals;
    }
}