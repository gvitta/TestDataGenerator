package com.dataGenerator.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class NumberGeneration {

    public static String generateInt(boolean seedFlag, Random random) {
        if (seedFlag == false)
            return Integer.toString(ThreadLocalRandom.current().nextInt());
        else
            return Integer.toString(random.nextInt());
    }

    public static String generatePositiveInt(boolean seedFlag, Random random) {
        if (seedFlag == false)
            return Integer.toString(abs(ThreadLocalRandom.current().nextInt()));
        else
            return Integer.toString(abs(random.nextInt()));
    }

    public static String generatePositiveInt(int bound, boolean seedFlag, Random random) {
        if (seedFlag == false)
            return Integer.toString(abs(ThreadLocalRandom.current().nextInt(bound)));
        else
            return Integer.toString(abs(random.nextInt(bound)));
    }

    public static String generateAmount(boolean seedFlag, Random random) {
        if (seedFlag == false)
            return Double.toString(ThreadLocalRandom.current().nextDouble());
        else
            return Double.toString(random.nextDouble());
    }

    public static String generateAmount(double bound, boolean seedFlag, Random random) {
        if (seedFlag == false)
            return Double.toString(ThreadLocalRandom.current().nextDouble(bound));
        else
            return Double.toString(random.nextDouble());
    }
}
