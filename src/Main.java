import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");

        Integer i = 0;
        int[] adr = new int[] {5,3};

        Client client1 = new Client(0, adr);

        ArrayList<Meal> meals1 = new ArrayList<Meal>();
        Meal meal1 = new Meal(Meal.FoodType.PIZZERIA, 5);
        //Meal meal2 = new Meal(Meal.FoodType.ROTISERIA, 10);
        meals1.add(meal1);
        Order order1 = OrderGenerator.generateOrder(i, client1, client1.getAddress(), 0, meals1);        
        
        Cooker cooker = new Cooker(2, Meal.FoodType.PIZZERIA);
        Business b1 = new Business(0); 
        b1.addCooker(cooker);

        BusinessSelector bs = new BusinessSelector();
        bs.addBusiness(b1);

        OrderHandler handler1 = new OrderHandler(bs);

        handler1.addOrder(order1);
        handler1.sendOrderToBusiness();
        b1.assignOrders();
        b1.onGoingOrders();
    }   
}