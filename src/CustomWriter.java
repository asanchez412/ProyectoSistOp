import java.io.PrintWriter;
import java.security.InvalidParameterException;

public class CustomWriter  {
    static PrintWriter wr;
    
    public static void setWriter(PrintWriter writer) {
        wr = writer;
    }
    
    public static void write(String[] toPrint) {
        if(toPrint.length == 6) {
            wr.println(String.format("Reloj: %s,Pedido: %s,Tipo de producto: %s,Estado: %s,Local: %s,Repartidor: %s", toPrint[0], toPrint[1], toPrint[2], toPrint[3], toPrint[4], toPrint[5]));
        }
        else if(toPrint.length == 1) {
            wr.println(toPrint[0]);
        }
        else {
            throw new InvalidParameterException(String.format("Expected length was 6 and received length was: ", toPrint.length));
        }
    }
}