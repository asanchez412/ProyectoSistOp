import java.util.ArrayList;
import java.util.HashMap;

public class OrderHandler {
    private ArrayList<Order> toDistribute = new ArrayList<Order>();
    private BusinessSelector selector;

    public OrderHandler(BusinessSelector selector) {
        this.selector = selector;
    }

    public void addOrder(Order order) {
        toDistribute.add(order);
    }

    public void sendOrderToBusiness() {
        while(toDistribute.size() > 0) {
            Order order = toDistribute.get(0);
            selector.selectBusiness(order);
            Business orderBusiness = order.getBusiness(); 
            if(orderBusiness != null) {
                orderBusiness.addOrder(order);
            }
            else {
                // TODO: Log failure
            }
            //Revisar
            toDistribute.remove(0);
        }
    }
}