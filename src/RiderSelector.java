import java.util.ArrayList;

public class RiderSelector{
    private ArrayList<Rider> ridersList;

    public void setRidersList(ArrayList<Rider> availableRiders) {
        ridersList = availableRiders;
    }

    public Rider selectRider(Order order) {
        Rider theRider;
        int riderCounter = 0;
        int riderPosition = 0;
        double distance = Double.MIN_VALUE;
        double minDistance = Double.MAX_VALUE;
        for (Rider rider : ridersList) {
            distance = Utils.calculateDistance(rider.getActualPosition(), order.getBusiness().getAddress());
            if (minDistance > distance){
                minDistance = distance;
                riderPosition = riderCounter;
            }
            riderCounter++;
        }
        theRider = ridersList.get(riderPosition);
        theRider.setOrder(order);
        theRider.setTime((int)distance);
        theRider.setAddress(order.getBusiness().getAddress());
        theRider.setState(Rider.State.BUSY);
        ridersList.remove(riderPosition);
        return theRider;
    }
}