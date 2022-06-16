import java.util.Map.Entry;

public class Rider implements Runnable{
    private Integer id;
    private Order order;
    private int[] actualPosition = new int[2]; 
    private Entry<Integer, Integer> address;

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                //atomicInteger.incrementAndGet();
            } catch (Exception e) {}
        }
    }

    public Integer getId(){
        return id;
    }
    
    public Order getOrder(){
        return order;
    }

    public int[] getActualPosition(){
        return actualPosition;
    }

    public Entry<Integer, Integer> getAddress(){
        return address;
    }
}