import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    
    public static void main(String[] args) throws Exception {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/log.log")));
        CustomWriter.setWriter(writer);
        CustomWriter.write(new String[] {"Tiempo", "Pedido" , "Tipo de producto", "Estado", "Local", "Repartidor"});
        
        OrderHandler orderHandler = new OrderHandler();
        OrderGenerator.setOrderHandler(orderHandler);
        OrderGenerator.generateOrder("src/entrada1.csv");
        BusinessAndCookerGenerator.setOrderHandler(orderHandler);
        BusinessAndCookerGenerator.generateBusinessAndCookers("src/locales.csv");
        Thread th = new Thread(orderHandler);
        th.start();

        while(atomicInteger.get() <= 10) {
            Thread.sleep(1000);
            atomicInteger.incrementAndGet();
        }
        writer.flush();
        writer.close();
    }   
}