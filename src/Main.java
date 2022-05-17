import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] adr = new int[] {5,3};

        Client client1 = new Client(0, adr);

        ArrayList<Meal> meals1 = new ArrayList<Meal>();
        Meal meal1 = new Meal(Meal.FoodType.PIZZERIA, 5);
        ArrayList<Meal> meals2 = new ArrayList<Meal>();
        Meal meal2 = new Meal(Meal.FoodType.PIZZERIA, 5);
        Meal meal3 = new Meal(Meal.FoodType.PIZZERIA, 8);
        meals1.add(meal1);
        meals2.add(meal2);
        meals2.add(meal3);
        Order order1 = OrderGenerator.generateOrder(0, client1, client1.getAddress(), 0, meals1);        
        Order order2 = OrderGenerator.generateOrder(1, client1, client1.getAddress(), 0, meals2);

        Cooker cooker1 = new Cooker(5, Meal.FoodType.PIZZERIA);
        Business b1 = new Business(0);  
        b1.addCooker(cooker1);

        BusinessSelector bs = new BusinessSelector();
        BusinessSelector.addBusiness(b1);

        OrderHandler handler1 = new OrderHandler(bs);

        handler1.addOrder(order1);
        handler1.addOrder(order2);
        handler1.sendOrderToBusiness();
        b1.assignOrders();
        b1.onGoingOrders();
    }   
}