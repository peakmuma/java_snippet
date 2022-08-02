package me.peak;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberTest {

    public static final BigDecimal HUNDRED = new BigDecimal(100);

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
}
