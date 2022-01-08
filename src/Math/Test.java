package Math;

import java.text.DecimalFormat;

public class Test {

    public static void main(String[] args) {


        for (int i = 0; i <= 7; i++) {
            double fx = Math.pow(Math.pow(2,i),7) + Math.pow(Math.pow(2,i),4) + 3 * Math.pow(2,i) + 1;
            System.out.println(fx);
        }

//        for (int i = 0; i < 10; i++) {
//            System.out.println(Math.pow(Math.E,i));
//            System.out.println(Math.pow(Math.E,i)-3 * Math.pow(i,2));
//
//        }



    }




    public static double getLiFangGen(double num) {
        if (num == 0) {
            return num;
        }

        double num1,num2;
        num1 = num;
        num2 = (2*num1/3)+(num/(num1*num1*3));//利用牛顿迭代法求解
        while(Math.abs(num2-num1)>0.000001){
            num1=num2;
            num2=(2*num1/3)+(num/(num1*num1*3));
        }
        DecimalFormat df = new DecimalFormat("#.0");
        return Double.parseDouble(df.format(num2));
    }

}
