import java.util.ArrayList;

public class BusinessSelector {

    private ArrayList<Business> businessList;
    
    public void setBusinessList(ArrayList<Business> availableBusiness) {
        businessList = availableBusiness;
    }

    public Business selectBusiness(Order order) {
        // TODO: planify taking into consideration empty queue slots and distance
        Boolean[] foodTypes = new Boolean[3];
        for (Meal meal : order.getMealsList()) {
            switch(meal.getFoodType()) {
                case PIZZERIA:
                    foodTypes[0] = true;
                    break;
                
                case ROTISERIA:
                    foodTypes[1] = true;
                    break;

                case CONFITERIA:
                    foodTypes[2] = true;
                    break;
            }
        }
        for (Business business : businessList) {
            var businessFoodTypes = business.getFoodTypes();
            if(foodTypes.length == businessFoodTypes.length) {
                if(foodTypes[0] == businessFoodTypes[0] && foodTypes[1] == businessFoodTypes[1] && foodTypes[2] == businessFoodTypes[2]) {
                    order.setBusiness(business);
                    return business;
                }
            }
        }
        order.setBusiness(null);
        return null;
    }
}
