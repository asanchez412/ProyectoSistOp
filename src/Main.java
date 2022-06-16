import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        Cooker cooker1 = new Cooker(5, Meal.FoodType.PIZZERIA);
        Business b1 = new Business(0); 
        b1.addCooker(cooker1);
        
        OrderHandler orderHandler = new OrderHandler();
        orderHandler.addBusiness(b1);
        OrderGenerator.setOrderHandler(orderHandler);
        OrderGenerator.generateOrder("src/entrada1.csv");
        Thread th = new Thread(orderHandler);
        th.run();

        while(atomicInteger.get() <= 60) {
            Thread.sleep(1000);
            atomicInteger.incrementAndGet();
        }

        CustomWriter.write(new String[] {"Tiempo", "Pedido" , "Tipo de producto", "Estado", "Local", "Repartidor"});
    }   
}