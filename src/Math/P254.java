package Math;

public class P254 {

    public static void main(String[] args) {
        double y = 1.2;
        for (int i = 1; i <=5; i++) {
            y = 0.2 * i * 0.2 + 1.2 * y;
            System.out.println(y);
        }
    }
}
