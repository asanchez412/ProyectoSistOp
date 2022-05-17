import java.util.ArrayList;

public class OrderGenerator {
    public static Order generateOrder(Integer id, Client client, int[] address, Integer orderStartTime, ArrayList<Meal> meal) {
        return new Order(id, client, address, orderStartTime, meal);
    }
}
