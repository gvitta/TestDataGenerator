package com.dataGenerator.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HelperFunction {
        String[] creditDebitOperator = new String[]{"C", "D"};
        String[] signOperator = new String[]{" ", "-"};
        private Random dateRandom = new Random();
        private Random random = new Random();
        private char[] operator = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        public HelperFunction() {
        }

        public static String generateString(String pattern) {
            String[] stringList = pattern.split(",");
            int i = ThreadLocalRandom.current().nextInt(0, stringList.length);
            return stringList[i];
        }

        public String generateSingleChar() {
            return this.creditDebitOperator[ThreadLocalRandom.current().nextInt(0, 2)];
        }

        public String generateStringOfChar(int actualWidth) {
            StringBuilder stringOfChar = new StringBuilder();

            for(int i = 0; i < actualWidth; ++i) {
                stringOfChar.append(this.operator[ThreadLocalRandom.current().nextInt(0, 26)]);
            }

            return stringOfChar.toString();
        }

        public static String padRight(int size, String data, String type) {
            StringBuilder builder = new StringBuilder(data);
            builder = builder.reverse();

            while(builder.length() < size) {
                if (type.equals("number")) {
                    builder.append('0');
                } else {
                    builder.append(' ');
                }
            }

            String reverse = builder.reverse().toString();
            return reverse;
        }

        public static String generateCurrency() {
            return "GBP";
        }

        public static String generateConstantValue(String pattern) {
            return pattern;
        }

        public String generateFixedInt(int actualWidth, int withPaddingWidth) {
            long randomLong = (long)(Math.random() * (double)Long.parseLong(StringUtils.rightPad("1", actualWidth, "0")));
            return String.format("%0" + withPaddingWidth + "d", randomLong);
        }

        public String generateFixedAmount(int actualWidth, int withPaddingWidth) {
            return this.generateFixedInt(actualWidth - 1, withPaddingWidth - 1) + this.signOperator[ThreadLocalRandom.current().nextInt(0, 1)];
        }

        public String generateFixedPositiveAmount(int actualWidth, int withPaddingWidth) {
            return this.generateFixedInt(actualWidth - 1, withPaddingWidth - 1) + " ";
        }

        public static String generateBoolean(int actualWidth) {
            StringBuilder booleanSeq = new StringBuilder();

            for(int i = 0; i < actualWidth; ++i) {
                booleanSeq.append(ThreadLocalRandom.current().nextInt(0, 2));
            }

            return booleanSeq.toString();
        }

        public String generateDate(String min, String max, String pattern) {
            int minDay = (int) LocalDate.parse(min).toEpochDay();
            int maxDay = (int)LocalDate.parse(max).toEpochDay();
            long randomDay = (long)(minDay + this.dateRandom.nextInt(maxDay - minDay));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate date = LocalDate.ofEpochDay(randomDay);
            return date.format(formatter);
        }

        public String generateInt() {
            return Integer.toString(this.random.nextInt());
        }

        public String generateAmount() {
            return Double.toString(this.random.nextDouble());
        }

        public String generateLong() {
            return Long.toString(this.random.nextLong());
        }

        public String generateBetweenRange(Long min, Long max) {
            long v = ThreadLocalRandom.current().nextLong(min, max);
            return Long.toString(v);
        }

}
