public class Meal {

    public enum FoodType {
        PIZZERIA,
        ROTISERIA,
        CONFITERIA,
    }
    
    private FoodType ft;
    private Integer remainingTime;
    private Cooker assignedCooker;

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

    public Cooker getAssignedCooker() {
        return assignedCooker;
    }

    public void setAssignedCooker(Cooker assignedCooker) {
        this.assignedCooker = assignedCooker;
    }

    public FoodType getFoodType() {
        return ft;
    }
}