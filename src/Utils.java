public class Utils{
    public static Double calculateDistance(int[] addr1, int[] addr2) {
        return Math.sqrt((addr2[1] - addr1[1]) * (addr2[1] - addr1[1]) + (addr2[0] - addr1[0]) * (addr2[0] - addr1[0]));
    }
}