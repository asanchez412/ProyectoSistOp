import java.util.ArrayList;
import java.util.Map.Entry;

public class Order {
    private Integer id;
    private Business business = null;
    private Client client;
    private int[] address;
    private Integer orderStartTime;
    private ArrayList<Meal> meals;
    
    public Order(Integer id, Client client, int[] address, Integer orderStartTime, ArrayList<Meal> meal) {
        this.id = id;
        this.client = client;
        this.address = address;
        this.orderStartTime = orderStartTime;
        this.meals = meal;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public ArrayList<Meal> getMealsList() {
        return meals;
    }
}
