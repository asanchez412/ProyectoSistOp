import java.util.List;
import java.util.Map.Entry;

public class Client {
    private Integer id;
    private List<Order> orders;
    private Entry<Integer, Integer> address;  

    public Client(Integer id, List<Order> orders, Entry<Integer, Integer> address) {
        this.id = id;
        this.orders = orders;
        this.address = address;
    }
}