import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderGenerator {
    private static OrderHandler orderHandler;
    private static HashMap<Integer, Client> clients = new HashMap<Integer, Client>();

    public static void generateOrder(String path) throws IOException {
        try {
            InputStream inputStream = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if(attributes.length == 5) {
                    String[] strAddress = attributes[3].split(" ");
                    if(strAddress.length != 2) {
                        CustomWriter.write(new String[] { String.format("Invalid address, line %s", line) });
                        continue;
                    }
                    int[] address = new int[] { Integer.parseInt(strAddress[0]), Integer.parseInt(strAddress[1]) };
                    Integer clientId = Integer.parseInt(attributes[2]);         
                    Client client = clients.get(clientId);
                    if(client == null) {
                        client = new Client(clientId, address);
                        clients.put(clientId, client);
                    }
                    Integer orderStartTime = Integer.parseInt(attributes[0]);
                    Integer orderId = Integer.parseInt(attributes[1]);
                    String[] strMeals = attributes[4].split(" ");
                    ArrayList<Meal> meals = new ArrayList<Meal>();
                    for (String strMeal : strMeals) {
                        String[] strTime = strMeal.split(":");
                        Integer timer = Integer.parseInt(attributes[1]);
                        Meal.FoodType ft = null;
                        switch(strTime[0]) {
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
                        meals.add(new Meal(ft, timer));
                    }   
                    orderHandler.addOrder(new Order(orderId, client, address, orderStartTime, meals));
                    CustomWriter.write(new String[] { String.format("Order %s generated, starting time: %s", orderId, orderStartTime) });
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
        OrderGenerator.orderHandler = orderHandler;
    }
}