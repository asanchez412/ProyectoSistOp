import java.util.List;
import java.util.Map.Entry;

public class Order {
    private Integer id;
    private Business business;
    private Client client;
    private Entry<Integer, Integer> address;
    private Integer orderStartTime;
    private List<Meal> meals;
    
    public Order(Integer id, Client client, Entry<Integer, Integer> address, Integer orderStartTime, Meal meal) {
        this.id = id;
        this.client = client;
        this.address = address;
        this.orderStartTime = orderStartTime;
        this.meals.add(meal);
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
