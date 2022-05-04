package com.dataGenerator.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.concurrent.ThreadLocalRandom;

public class FixedLengthGeneration {
    private static String[] signOperator = {" ", "-"};

    public static String padRight(int size, String data, String type, boolean seedFlag) {
        StringBuilder builder = new StringBuilder(data);
        builder = builder.reverse();
        while (builder.length() < size) {
            if (type.equals("number"))
                builder.append('0');
            else
                builder.append(' ');
        }
        String reverse = builder.reverse().toString();
        return reverse;
    }

    public static String generateFixedInt(int actualWidth, int withPaddingWidth, boolean seedFlag) {
        long randomLong = (long) (Math.random() * (Long.parseLong(StringUtils.rightPad("1", actualWidth, "0"))));
        return String.format("%0" + withPaddingWidth + "d", randomLong);
    }

    public static String generateFixedAmount(int actualWidth, int withPaddingWidth, boolean seedFlag) {
        return generateFixedInt(actualWidth - 1, withPaddingWidth - 1,seedFlag) + signOperator[ThreadLocalRandom.current().nextInt(0, 1)];
    }

    public static String generateFixedPositiveAmount(int actualWidth, int withPaddingWidth, boolean seedFlag) {
        return generateFixedInt(actualWidth - 1, withPaddingWidth - 1,seedFlag) + " ";
    }

}
