import java.util.HashMap;
import java.util.ArrayList;

public class Business {
    private Integer id;
    private int[] address = new int[2]; 
    private ArrayList<Order> notAssignedOrders = new ArrayList<Order>();
    private ArrayList<Order> onGoingOrders = new ArrayList<Order>();
    private Boolean[] foodTypes = new Boolean[3];
    private HashMap<Meal.FoodType, Cooker> cookers = new HashMap<Meal.FoodType, Cooker>(); 
    
    public Business(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int[] getAddress() {
        return address;
    }

    public void addOrder(Order order) {
        notAssignedOrders.add(order);
    }

    public void assignOrders() {
        while(notAssignedOrders.size() > 0) {
            Order order = notAssignedOrders.get(0);
            var meals = order.getMealsList();
            Boolean added = false;
            for(Meal meal : meals) {
                Cooker cooker = cookers.get(meal.getFoodType());
                if(cooker != null) {
                    meal.setAssignedCooker(cooker);
                    cooker.addMeal(meal);
                    if(!added) {
                        onGoingOrders.add(order);
                        added = true;
                    }
                    cooker.enqueueMeals();
                }        
            }
            notAssignedOrders.remove(0);
        }
    }

    public void onGoingOrders() {
        for (Order order : onGoingOrders) {
            for (Meal meal : order.getMealsList()) {
                meal.getAssignedCooker().cook();
                System.out.println("ended cooking order: " + order.getId() + " from business: " + this.id);
            }
        }
    }

    public void addCooker(Cooker cooker) {
        cookers.put(cooker.getArea(), cooker);
        switch(cooker.getArea()) {
            case PIZZERIA:
                foodTypes[0] = true;
                break;
                
            case ROTISERIA:
                foodTypes[1] = true;
                break;

            case CONFITERIA:
                foodTypes[2] = true;
                break;
        }
    }

    public Boolean[] getFoodTypes() {
        return foodTypes;
    }

    public HashMap<Meal.FoodType, Cooker> getCookers() {
        return cookers;
    }
}