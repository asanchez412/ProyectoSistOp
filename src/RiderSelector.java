import java.util.ArrayList;

public class RiderSelector{
    private ArrayList<Rider> ridersList;
    
    public void setBusinessList(ArrayList<Rider> availableRiders) {
        ridersList = availableRiders;
    }

    public Rider selectRider(Business business) {
        int riderCounter = 0;
        int riderPosition = 0;
        double distance;
        double minDistance = Double.MAX_VALUE;
        for (Rider rider : ridersList) {
            riderCounter++;
            distance = Utils.calculateDistance(rider.getActualPosition(), business.getAddress());
            if (minDistance > distance){
                minDistance = distance;
                riderPosition = riderCounter;
            }
        }
        return ridersList.get(riderPosition);
    }
}