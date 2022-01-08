package Math;

public class P20 {

    public static void main(String[] args) {

        int N = 1000000;

        float Sn = 0;

        int i = 2;
        while (i <= N){
            Sn = Sn +  (float) (1 / (Math.pow(i,2) - 1));
            i++;
        }

        System.out.println(Sn);


    }
}
