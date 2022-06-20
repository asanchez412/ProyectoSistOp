import java.util.ArrayList;

public class Order {
    private Integer id;
    private Business business = null;
    private Client client;
    private int[] address;
    private Integer orderStartTime;
    private ArrayList<Meal> meals;
    private Rider rider = null;
    
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

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public ArrayList<Meal> getMealsList() {
        return meals;
    }

    public Integer getId() {
        return id;
    }

    public Order clone() {
        Order newOrder = new Order(id, client, address, orderStartTime, meals);
        newOrder.setBusiness(business);
        return newOrder;
    }
}
