package com.dataGenerator.utils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TextGeneration {
    private static String[] signOperator = {" ", "-"};
    private static char[] operator = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};

    public static String generateString(String pattern, boolean seedFlag, Random random) {
        String[] stringList = pattern.split(",");
        if (seedFlag == false)
            return stringList[ThreadLocalRandom.current().nextInt(stringList.length)];
        else
            return stringList[random.nextInt(stringList.length)];
    }

    public static String generateStringOfChar(int actualWidth, boolean seedFlag, Random random) {
        StringBuilder stringOfChar = new StringBuilder();
        for (int i = 0; i < actualWidth; i++) {
            if (seedFlag == false)
                stringOfChar.append(operator[ThreadLocalRandom.current().nextInt(0, 27)]);
            else
                stringOfChar.append(operator[random.nextInt(27)]);
        }
        return stringOfChar.toString();
    }

    public static String generateFirstName(List<String> first_names, boolean seedFlag, Random random) {
        if (seedFlag == false)
            return "\t"+ first_names.get(ThreadLocalRandom.current().nextInt(first_names.size())) + "\t";
        else
            return "\t"+ first_names.get(random.nextInt(first_names.size())) +"\t";
    }

    public static String generateLastName(List<String> last_names,boolean seedFlag, Random random) {
        if (seedFlag == false)
            return "\t"+ last_names.get(ThreadLocalRandom.current().nextInt(last_names.size())) + "\t\t";
        else
            return "\t"+ last_names.get(random.nextInt(last_names.size())) +"\t\t";
    }

    public static String generateFullName(List<String> first_names,List<String> last_names,boolean seedFlag, Random random) {
        if (seedFlag == false)
            return "\t"+ first_names.get(ThreadLocalRandom.current().nextInt(first_names.size())) +"\t"+
                    last_names.get(ThreadLocalRandom.current().nextInt(last_names.size())) +"\t\t";
        else
            return "\t"+ first_names.get(random.nextInt(first_names.size())) +"\t"+
                    last_names.get(random.nextInt(last_names.size())) +"\t\t";
    }

}
