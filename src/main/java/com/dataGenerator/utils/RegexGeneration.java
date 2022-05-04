package com.dataGenerator.utils;

import nl.flotsam.xeger.Xeger;
import java.util.Random;

public class RegexGeneration {
    private static Xeger generator;

    public static String generateStringFromRegex(String pattern, boolean seedFlag, Random random){
        generator = new Xeger(pattern);
        if (seedFlag == false)
            generator.getRandom();
        else
            generator.setRandom(random);

        return generator.generate();
    }

}
