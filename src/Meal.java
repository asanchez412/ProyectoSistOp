public class Meal {

    public enum FoodType {
        PIZZERIA,
        ROTISERIA,
        CONFITERIA,
    }
    
    private FoodType ft;
    private Integer remainingTime;
    private Boolean status = false; 

    public Meal(FoodType area, Integer timer) {
        ft = area;
        remainingTime = timer;
    }

    public void cook() {
        if(remainingTime > 0) {
            remainingTime--;
        }
        else {
            status = true;
        }
    }

    public FoodType getFoodType() {
        return ft;
    }

    public Boolean isCooked() {
        return status;
    }
}