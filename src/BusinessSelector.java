import java.util.ArrayList;

public class BusinessSelector {

    private ArrayList<Business> businessList;
    
    public void setBusinessList(ArrayList<Business> availableBusiness) {
        businessList = availableBusiness;
    }

    public Business selectBusiness(Order order) {
        ArrayList<Business> suitableBusinessList = new ArrayList<Business>();
        Boolean[] foodTypes = new Boolean[] {false, false, false};
        Integer i = 0;
        for (Meal meal : order.getMealsList()) {
            switch(meal.getFoodType()) {
                case PIZZERIA:
                    foodTypes[0] = true;
                    i++;
                    break;
                case ROTISERIA:
                    foodTypes[1] = true;
                    i++;
                    break;
                case CONFITERIA:
                    foodTypes[2] = true;
                    i++;
                    break;
            }
        }
        for (Business business : businessList) {
            var businessFoodTypes = business.getFoodTypes();
            if(foodTypes.length == businessFoodTypes.length) {
                switch(i) {
                    case 1:
                        if(foodTypes[0]) {
                            if(businessFoodTypes[0]) {
                                suitableBusinessList.add(business);
                            }
                        }
                        else if(foodTypes[1]) {
                            if(businessFoodTypes[1]) {
                                suitableBusinessList.add(business);
                            }
                        }
                        else if(foodTypes[2]) {
                            if(businessFoodTypes[2]) {
                                suitableBusinessList.add(business);
                            }
                        }
                        break;
                    case 2:
                        if(foodTypes[0] && foodTypes[1]) {
                            if(businessFoodTypes[0] && businessFoodTypes[1]) {
                                suitableBusinessList.add(business);
                            }
                        }
                        else if(foodTypes[0] && foodTypes[2]) {
                            if(businessFoodTypes[0] && businessFoodTypes[2]) {
                                suitableBusinessList.add(business);
                            }
                        }
                        else if(foodTypes[1] && foodTypes[2]) {
                            if(businessFoodTypes[1] && businessFoodTypes[2]) {
                                suitableBusinessList.add(business);
                            }
                        }
                        break;
                    case 3:
                        if(foodTypes[1] && foodTypes[2] && foodTypes[3]) {
                            if(businessFoodTypes[1] && businessFoodTypes[2] && businessFoodTypes[3]) {
                                suitableBusinessList.add(business);
                            }
                        }
                        break;
                }
            }
        }
        int position = -1;
        int minCounter = Integer.MAX_VALUE;
        int j = 0;
        for (Business business : suitableBusinessList) {
            if(minCounter > business.getOrderCounter()) {
                minCounter = business.getOrderCounter();
                position = j;
            }
            j++;
        }
        if(position == -1) {
            return null;
        }
        Business business = suitableBusinessList.get(position);
        business.setOrderCounter(business.getOrderCounter()+1);
        order.setBusiness(business);
        return suitableBusinessList.get(position);
    }
}
