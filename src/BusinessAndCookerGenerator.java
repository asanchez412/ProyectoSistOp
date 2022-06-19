import java.io.*;
import java.util.ArrayList;

public class BusinessAndCookerGenerator {
    private static OrderHandler orderHandler;
    
    public static void generateBusinessAndCookers(String path) throws IOException {
        try {
            InputStream inputStream = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            reader.readLine(); // skip header
            while((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if(attributes.length == 3) {
                    String[] strAddress = attributes[1].split("=");
                    if(strAddress.length != 2) {
                        CustomWriter.write(new String[] { String.format("Invalid address, line %s", line) });
                        continue;
                    }
                    int[] address = new int[] { Integer.parseInt(strAddress[0]), Integer.parseInt(strAddress[1]) };
                    Integer businessId = Integer.parseInt(attributes[0]);
                    String[] strCookers = attributes[2].split("#");
                    ArrayList<Cooker> cookers = new ArrayList<Cooker>();
                    for (String strCooker : strCookers) {
                        String[] strSimulMeals = strCooker.split("=");
                        Integer simultaneousMeals = Integer.parseInt(strSimulMeals[1]);
                        Meal.FoodType ft = null;
                        switch(strSimulMeals[0]) {
                            case "1":
                                ft = Meal.FoodType.PIZZERIA;
                                break;
                            case "2":
                                ft = Meal.FoodType.ROTISERIA;
                                break;
                            case "3":
                                ft = Meal.FoodType.CONFITERIA;
                                break;
                            default:
                                CustomWriter.write(new String[] { String.format("Invalid food type, line %s", line) });
                                break;
                        }
                        cookers.add(new Cooker(simultaneousMeals, ft));
                    }
                    Business business = new Business(businessId, address);
                    orderHandler.addBusiness(business);
                    CustomWriter.write(new String[] { String.format("Business %s generated, address(x,y): %s,%s", businessId, strAddress[0], strAddress[1]) });   
                    for (Cooker cooker : cookers) {
                        business.addCooker(cooker);
                        CustomWriter.write(new String[] { String.format("Cooker with foodType %s added to previous business, simultaneous meals: %s", cooker.getArea(), cooker.getMaxMeals()) });
                    }   
                }
                else {
                    CustomWriter.write(new String[] { String.format("Invalid line format, line %s", line) });
                    continue;
                }
            }
            reader.close();
        } catch (IOException e) {
            CustomWriter.write(new String[] { String.format("Invalid path %s", path) });
            return;
        }
    }

    public static void setOrderHandler(OrderHandler orderHandler) {
        BusinessAndCookerGenerator.orderHandler = orderHandler;
    }
}
