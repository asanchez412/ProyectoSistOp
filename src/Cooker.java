import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Cooker {
    private Integer maxMeals;
    private Integer currentMeals = 0;
    private Meal.FoodType area;
    private ArrayList<Meal> toCook = new ArrayList<Meal>();
    private ArrayList<Meal> cooking = new ArrayList<Meal>();
    
    private Semaphore semaphore = new Semaphore(1);

    public Cooker(Integer maxSimultaneousMeals, Meal.FoodType area) {
        this.area = area;
        this.maxMeals = maxSimultaneousMeals;
    }

    public Meal.FoodType getArea() {
        return area;
    }

    public void enqueueMeals(Integer i) {
        try {
            while(toCook.size() > 0) {
                if(currentMeals <= maxMeals) {
                    semaphore.acquire();
                    Meal meal = toCook.get(0);
                    cooking.add(meal);
                    toCook.remove(0);
                    currentMeals++;
                    semaphore.release();
                    CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(meal.getOrder().getId()), meal.getFoodType().toString(), "Comenzó a prepararse", Integer.toString(meal.getOrder().getBusiness().getId()), "Asignado"});
                }
                else {
                    break;
                }
            }
        } catch (Exception e) { semaphore.release(); }
    }
    
    public void cook(Integer i) {
        try {
            semaphore.acquire();
            for (Meal meal : cooking) {
                if(meal.isCooked()) {
                    cooking.remove(meal);
                    currentMeals--;
                    continue;
                }
                meal.cook();
            }
            semaphore.release();
        } catch (Exception e) { semaphore.release(); }

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