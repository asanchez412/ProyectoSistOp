import java.util.ArrayList;
import java.util.Map.Entry;

public class Rider implements Runnable{
    private Integer id;
    private Order order;
    private int[] actualPosition = new int[2]; 
    private int[] address = new int[2]; 
    private State state = State.PASSIVE;
    private OrderState orderState = OrderState.WITHOUT_ORDER;
    private Integer time = 0;
    private int i = 1;

    public Rider(Integer id, int[] pPosition) {
        this.id = id;
        this.actualPosition = pPosition;
    }

    public enum State{
        BUSY,
        PASSIVE
    }

    public enum OrderState{
        WITH_ORDER,
        WITHOUT_ORDER
    }

    //region Getters
    public Integer getId(){
        return id;
    }

    public Order getOrder(){
        return order;
    }

    public int[] getActualPosition(){
        return actualPosition;
    }

    public int[] getAddress(){
        return address;
    }

    public State getState(){
        return state;
    }

    public OrderState getOrderState(){
        return orderState;
    }

    public Integer getTime(){
        return time;
    }
    //endregion

    //region Setters
    public void setOrder(Order order){
        this.order = order;
    }

    public void setActualPosition(int[] position){
        actualPosition = position;
    }

    public void setAddress(int[] position){
        address = position;
    }

    public void setState(Rider.State state){
        this.state = state;
    }

    public void setOrderState(Rider.OrderState state){
        this.orderState = state;
    }

    public void setTime(int time){
        this.time = time;
    }
    //endregion

    public void onGoingRider() {
        //y tambien le cambiamos el estado del repartidor
    }

    public void moveRider(){
        if (state == Rider.State.BUSY){
            time--;
            if (time <= 0){
                actualPosition = address;
            }
        }
    }

    @Override
    public void run() {
        /*
        while(true) {
            if(i == Main.atomicInteger.get()) {
                moveRider();
                i++;
            }
            else if (i < Main.atomicInteger.get()) {
                i = Main.atomicInteger.get();
            }
        }
         */
    }
}