import java.io.*;
import java.util.ArrayList;

public class RiderGenerator {
    private static OrderHandler orderHandler;
    
    public static void generateRiders(String path) throws IOException {
        try {
            InputStream inputStream = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            reader.readLine(); // skip header
            while((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if(attributes.length == 2) {
                    String[] strAddress = attributes[1].split("=");
                    if(strAddress.length != 2) {
                        CustomWriter.write(new String[] { String.format("Invalid address, line %s", line) });
                        continue;
                    }
                    int[] address = new int[] { Integer.parseInt(strAddress[0]), Integer.parseInt(strAddress[1]) };
                    Integer riderId = Integer.parseInt(attributes[0]);
                    Rider rider = new Rider(riderId, address);
                    orderHandler.addRiders(rider);

                    CustomWriter.write(new String[] { String.format("Rider %s generated, address(x,y): %s,%s", riderId, strAddress[0], strAddress[1]) }); 
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
        RiderGenerator.orderHandler = orderHandler;
    }
}
