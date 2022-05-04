package com.dataGenerator.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OtherGeneration {

    public static String generateCurrency(String pattern, String[] currency, boolean seedFlag, Random random) {
        if(pattern != null && pattern !="")
            currency=pattern.split(",");

        if (seedFlag == false)
            return currency[ThreadLocalRandom.current().nextInt(currency.length)];
        else
            return currency[random.nextInt(currency.length)];
    }

    public static String generateBoolean(String pattern, boolean seedFlag, Random random) {
        String vals[] = pattern.split("/");
        if (seedFlag == false)
            return vals[ThreadLocalRandom.current().nextInt(vals.length)];
        else
            return vals[random.nextInt(vals.length)];
    }

    public static String generateConstantValue(String pattern) {
        return pattern;
    }

}
