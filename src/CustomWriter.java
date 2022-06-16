import java.io.*;
import java.security.InvalidParameterException;

public class CustomWriter {
    public static void write(String[] toPrint) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/log.log")));
            if(toPrint.length == 6) {
                writer.println(String.format("Reloj: %s,Pedido: %s,Tipo de producto: %s,Estado: %s,Local: %s,Repartidor: %s", toPrint[0], toPrint[1], toPrint[2], toPrint[3], toPrint[4], toPrint[5]));
            }
            else if (toPrint.length == 1) {
                writer.println(toPrint[0]);
            }
            else {
                writer.close();
                throw new InvalidParameterException(String.format("Expected length was 6 and received length was: ", toPrint.length));
            }
            writer.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}