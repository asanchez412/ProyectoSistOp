import java.io.*;
import java.security.InvalidParameterException;

public class CustomWriter {
    public static void write(String[] toPrint) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Results\\log.log")));
        if(toPrint.length == 7) {
            writer.println(String.format("%s,%s,%s,%s,%s,%s,%s", toPrint[0], toPrint[1], toPrint[2], toPrint[3], toPrint[4], toPrint[5], toPrint[6]));
        }
        else if (toPrint.length == 1) {
            writer.println(toPrint[0]);
        }
        else {
            writer.close();
            throw new InvalidParameterException(String.format("Expected length was 7 and received length was: ", toPrint.length));
        }
        writer.close();
    }
}