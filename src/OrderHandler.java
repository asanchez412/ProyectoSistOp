import java.util.ArrayList;

public class OrderHandler implements Runnable {
    private ArrayList<Order> toDistribute = new ArrayList<Order>();
    private BusinessSelector selector = new BusinessSelector();
    private RiderSelector riderSelector = new RiderSelector();
    private ArrayList<Business> availableBusiness = new ArrayList<Business>();
    private int i = 1;

    public void addOrder(Order order) {
        toDistribute.add(order);
    }

    public void sendOrderToBusiness() {
        Order order = toDistribute.get(0);
        selector.selectBusiness(order);
        Business business = order.getBusiness(); 
        if(business != null) {
            business.addOrder(order.clone());
        }
        else {
            // TODO: Log failure
        }
        toDistribute.remove(0);
        CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(order.getId()), "Orden completa", "Asignado", Integer.toString(order.getBusiness().getId()), "No asignado"});
    }
    
    public void addBusiness(Business business) {
        availableBusiness.add(business);
    }

    public ArrayList<Business> getBusiness() {
        return availableBusiness;
    }

    @Override
    public void run() {
        selector.setBusinessList(availableBusiness);
        while(true) {
            if(i == Main.atomicInteger.get()) {
                if(toDistribute.size() > 0) {
                    sendOrderToBusiness();
                }
                i++;    
            }
        }
    }
}