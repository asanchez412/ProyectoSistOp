package src;
public class Meal {
    private enum FoodType {
        PIZZERIA,
        ROTISERIA,
        CONFITERIA,
    }
    
    private FoodType ft;
    private Integer remainingTime;

    public Meal(FoodType area, Integer timer) {
        ft = area;
        remainingTime = timer;
    }

    public Boolean cook() {
        while(remainingTime > 0) {
            remainingTime--;
        }   
        return true;
    }
}