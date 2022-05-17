import java.util.ArrayList;

public class Client {
    private Integer id;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private int[] address = new int[2]; 

    public Client(Integer id, int[] address) {
        this.id = id;
        this.address = address;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int[] getAddress() {
        return address;
    }
}