package com.dataGenerator.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DateGeneration {
    public static String generateDate(String min, String max, String pattern, boolean seedFlag, Random random) {
        int minDay = (int) LocalDate.parse(min).toEpochDay();
        int maxDay = (int) LocalDate.parse(max).toEpochDay();
        long randomDay;
        if (seedFlag == false)
            randomDay = minDay + ThreadLocalRandom.current().nextInt(maxDay - minDay);
        else
            randomDay = minDay + random.nextInt(maxDay - minDay);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.ofEpochDay(randomDay);
        //System.out.println(date.format(formatter));
        return date.format(formatter);
    }
}
