import java.util.HashMap;
import java.util.ArrayList;

public class Business implements Runnable {
    private Integer id;
    private int[] address; 
    private ArrayList<Order> notAssignedOrders = new ArrayList<Order>();
    private ArrayList<Order> onGoingOrders = new ArrayList<Order>();
    private ArrayList<Order> readyOrders = new ArrayList<Order>();
    private Boolean[] foodTypes = new Boolean[3];
    private HashMap<Meal.FoodType, Cooker> cookers = new HashMap<Meal.FoodType, Cooker>(); 
    private int i = 1;
    
    public Business(Integer id, int[] address) {
        this.id = id;
        this.address = address;
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
            ArrayList<Meal> meals = order.getMealsList();
            for(Meal meal : meals) {
                Cooker cooker = cookers.get(meal.getFoodType());
                if(cooker != null) {;
                    cooker.addMeal(meal);
                }        
            }
            onGoingOrders.add(order);
            CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(order.getId()), "Orden completa", "Asignada a cocinero(s)", Integer.toString(order.getBusiness().getId()), "Asignado"});
            notAssignedOrders.remove(0);
        }
    }

    public void cook() {
        for (Cooker cooker : cookers.values()) {
            cooker.enqueueMeals(i);
            cooker.cook(i);
        }
    }

    public void checkOnGoingOrders() {
        for (Order order : onGoingOrders) {
            for (Meal meal : order.getMealsList()) {
                if(!meal.isCooked()) {
                    break;
                } 
            }
            readyOrders.add(order);
            onGoingOrders.remove(order);
            CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(order.getId()), "Orden completa", "Esperando repartidor", Integer.toString(order.getBusiness().getId()), "Asignado"});
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

    @Override
    public void run() {
        while(true) {
            if(i == Main.atomicInteger.get()) {
                cook();
                if(onGoingOrders.size() > 0) {
                    checkOnGoingOrders();
                }
                if(notAssignedOrders.size() > 0) {
                    assignOrders();
                }
                i++;
            }
        }
    }
}