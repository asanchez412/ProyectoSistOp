import java.util.ArrayList;

public class OrderHandler implements Runnable {
    private ArrayList<Order> toDistribute = new ArrayList<Order>();
    private BusinessSelector selector = new BusinessSelector();
    private RiderSelector riderSelector = new RiderSelector();
    private ArrayList<Business> availableBusiness = new ArrayList<Business>();
    private int i = 0;

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
            CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(order.getId()), "not assigned", "Asignado", Integer.toString(order.getBusiness().getId()), "No asignado"});
        }
    }
    
    public void addBusiness(Business business) {
        availableBusiness.add(business);
    }

    @Override
    public void run() {
        selector.setBusinessList(availableBusiness);
        while(true) {
            if(i == Main.atomicInteger.get()) {
                sendOrderToBusiness();
                i++;
            }
        }
    }
}