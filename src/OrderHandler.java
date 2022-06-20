import java.util.ArrayList;

public class OrderHandler implements Runnable {
    private ArrayList<Order> toDistribute = new ArrayList<Order>();
    private BusinessSelector selector = new BusinessSelector();
    private ArrayList<Business> availableBusiness = new ArrayList<Business>();
    private int i = 1;

    private RiderSelector riderSelector = new RiderSelector();
    private ArrayList<Rider> availableRiders = new ArrayList<Rider>();
    
    public void addOrder(Order order) {
        toDistribute.add(order);
    }

    public Order sendOrderToBusiness() {
        Order order = toDistribute.get(0);
        selector.selectBusiness(order);
        Business business = order.getBusiness(); 
        if(business != null) {
            business.addOrder(order);
        }
        else {
            return null;
        }
        toDistribute.remove(0);
        CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(order.getId()), "Orden completa", "Asignado", Integer.toString(order.getBusiness().getId()), "No asignado"});
        return order;
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
        riderSelector.setRidersList(availableRiders);
        while(true) {
            if(i == Main.atomicInteger.get()) {
                if(toDistribute.size() > 0) {
                    Order ord = sendOrderToBusiness();
                    sendOrderToRider(ord);
                }
                i++;    
            }
        }
    }

    public ArrayList<Rider> getRiders() {
        return availableRiders;
    }

    public void addRiders(Rider rider) {
        availableRiders.add(rider);
    }

    public void sendOrderToRider(Order order) {
        Rider rider = riderSelector.selectRider(order);
        if(rider != null){
            order.setRider(rider);
        }
        else {
            // TODO: Log failure
        }
        CustomWriter.write(new String[] {Integer.toString(i), Integer.toString(order.getId()), "Orden completa", "Asignado", Integer.toString(order.getBusiness().getId()), "Asignado a rider: " + rider.getId().toString()});
    }
    
}