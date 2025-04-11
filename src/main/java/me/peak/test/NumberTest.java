package me.peak.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberTest {

    public static final BigDecimal HUNDRED = new BigDecimal(100);

    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal("8628834");
        BigDecimal num2 = new BigDecimal("10570218");
        System.out.println(calcPercent(num1, num2, 3));
        System.out.println(calcPercentAndFormat(num1, num2, 3));
        System.out.println(calcPercentAndFormat1(num1, num2, 3));
    }

    public static BigDecimal calcPercent(BigDecimal num1, BigDecimal num2, int scale) {
        if (num2 == null || num2.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        if (num1 == null || num1.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        return num1.subtract(num2).divide(num1, scale, RoundingMode.HALF_UP);
    }

    public static String calcPercentAndFormat(BigDecimal num1, BigDecimal num2, int scale) {
        if (num2 == null || num2.compareTo(BigDecimal.ZERO) == 0) {
            return "";
        }
        if (num1 == null || num1.compareTo(BigDecimal.ZERO) == 0) {
            return "100%";
        }
        return num1.subtract(num2).divide(num1, RoundingMode.HALF_UP).multiply(HUNDRED).setScale(scale, RoundingMode.HALF_UP) + "%";
    }

    public static String calcPercentAndFormat1(BigDecimal num1, BigDecimal num2, int scale) {
        BigDecimal res = calcPercent(num1, num2, 9);
        if (res == null) {
            return "";
        } else {
            return res.multiply(HUNDRED).setScale(scale, RoundingMode.HALF_UP) + "%";
        }
    }
}
