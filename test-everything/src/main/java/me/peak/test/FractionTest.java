package me.peak.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class FractionTest {
    public static int max = 1000000000;

    public static int totalTime = 10000000;

    public static BigDecimal firstDivid100 = new BigDecimal(100);
    public static BigDecimal secondDivid1000 = new BigDecimal(1000);

    public static BigDecimal firstDivid10000 = new BigDecimal(10000);
    public static BigDecimal secondDivid10 = new BigDecimal(10);

    public static BigDecimal RESULT = new BigDecimal(10000);
    public static void main(String[] args) {
        //生成随机数，范围 0 到 10,0000,0000
        //使用 10,0000,0000 减去生成的数字 得到另一个数字
        int reserveSevenError = 0;
        int reserveFiveError = 0;
        int halfEvenError = 0;
        Random r = new Random();
        for (int i = 0; i < totalTime; i++) {
            int num1 = r.nextInt(max);
            int num2 = max - num1;
            if (!RESULT.equals(divideAndSum(num1, num2, firstDivid100, secondDivid1000))) {
                reserveSevenError++;
            }
            if (!RESULT.equals(divideAndSum(num1, num2, firstDivid10000, secondDivid10))) {
                reserveFiveError++;
            }
            if (!RESULT.equals(halfEvenUp(num1, num2, firstDivid10000, secondDivid10))) {
                halfEvenError++;
            }
        }
        System.out.println("total : " + totalTime);
        System.out.println("seven : " + reserveSevenError);
        System.out.println("five : " + reserveFiveError);
        System.out.println("halfEven : " + halfEvenError);
    }


    private static BigDecimal divideAndSum(int num1, int num2, BigDecimal firstDivid, BigDecimal secDivid) {
        BigDecimal a = new BigDecimal(num1);
        BigDecimal b = new BigDecimal(num2);
        //第一次除法
        BigDecimal c = a.divide(firstDivid, RoundingMode.HALF_UP);
        BigDecimal d = b.divide(firstDivid, RoundingMode.HALF_UP);
        //第二次除法
        BigDecimal e = c.divide(secDivid, RoundingMode.HALF_UP);
        BigDecimal f = d.divide(secDivid, RoundingMode.HALF_UP);

        BigDecimal res = e.add(f);
//        System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + res);
        return res;
    }

    private static BigDecimal halfEvenUp(int num1, int num2, BigDecimal firstDivid, BigDecimal secDivid) {
        BigDecimal a = new BigDecimal(num1);
        BigDecimal b = new BigDecimal(num2);
        //第一次除法
        BigDecimal c = a.divide(firstDivid, RoundingMode.HALF_UP);
        BigDecimal d = b.divide(firstDivid, RoundingMode.HALF_UP);
        //第二次除法
        BigDecimal e = c.divide(secDivid, RoundingMode.HALF_EVEN);
        BigDecimal f = d.divide(secDivid, RoundingMode.HALF_EVEN);

        BigDecimal res = e.add(f);
//        System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + res);
        return res;
    }


}
