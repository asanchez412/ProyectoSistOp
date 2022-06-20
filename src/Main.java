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
        for (Business business : orderHandler.getBusiness()) {
            Thread thr = new Thread(business);
            thr.start();
        }

        while(atomicInteger.get() <= 30) {
            Thread.sleep(200);
            atomicInteger.incrementAndGet();
        }
        writer.flush();
        writer.close();

        //2,3=1,1=3#3=2
        //3,2=2,1=2
    }   
}