package com.dataGenerator.utils;

import com.dataGenerator.json.ConfigurationReaderUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class GenerateData {
    private Random random;
    private static String[] currency = ConfigurationReaderUtils.getProperty("currencyList").split(",");
    private static List<String> first_names;
    private static List<String> last_names;

    public GenerateData(long seed) throws IOException {
        this.random = new Random(seed);
        this.first_names = Files.readAllLines(Paths.get(ConfigurationReaderUtils.getProperty("additionalResources")+""+
                ConfigurationReaderUtils.getProperty("firstNamesFile")));
        this.last_names  = Files.readAllLines(Paths.get(ConfigurationReaderUtils.getProperty("additionalResources")+""+
                ConfigurationReaderUtils.getProperty("lastNamesFile")));
    }

    public String generate(String methodType, int width, String pattern, String min, String max, boolean seedFlag) {
        switch (methodType) {
            case "generateInt":
                return com.dataGenerator.utils.NumberGeneration.generateInt(seedFlag, random);
            case "generatePositiveInt":
                if (pattern == null)
                    return com.dataGenerator.utils.NumberGeneration.generatePositiveInt(seedFlag, random);
                else
                    return com.dataGenerator.utils.NumberGeneration.generatePositiveInt(Integer.parseInt(pattern), seedFlag, random);
            case "generateAmount":
                return com.dataGenerator.utils.NumberGeneration.generateAmount(seedFlag, random);
            case "generateFirstName":
                return com.dataGenerator.utils.TextGeneration.generateFirstName(first_names, seedFlag, random);
            case "generateLastName":
                return com.dataGenerator.utils.TextGeneration.generateLastName(last_names, seedFlag, random);
            case "generateFullName":
                return com.dataGenerator.utils.TextGeneration.generateFullName(first_names, last_names, seedFlag, random);
            case "generateDate":
                return com.dataGenerator.utils.DateGeneration.generateDate(min, max, pattern, seedFlag, random);
            case "generateBoolean":
                return com.dataGenerator.utils.OtherGeneration.generateBoolean(pattern, seedFlag, random);
            case "generateCurrency":
                return com.dataGenerator.utils.OtherGeneration.generateCurrency(pattern, currency, seedFlag, random);
            case "generateString":
                return com.dataGenerator.utils.TextGeneration.generateString(pattern, seedFlag, random);
            case "generateStringOfChar":
                return com.dataGenerator.utils.TextGeneration.generateStringOfChar(width, seedFlag, random);
            case "generatePositiveAmount":
                if (pattern == null)
                    return com.dataGenerator.utils.NumberGeneration.generateAmount(seedFlag, random);
                else
                    return com.dataGenerator.utils.NumberGeneration.generateAmount(Double.parseDouble(pattern), seedFlag, random);
            case "generateStringFromRegex":
                return com.dataGenerator.utils.RegexGeneration.generateStringFromRegex(pattern, seedFlag, random);
            case "generateConstantValue":
                return com.dataGenerator.utils.OtherGeneration.generateConstantValue(pattern);
            default:
                return null;
        }
    }

    public String generateFixedLength(String methodType, int width, int withoutpad, String pattern, String min, String max, boolean seedFlag) {
        switch (methodType) {
            case "generateInt":
                return com.dataGenerator.utils.FixedLengthGeneration.generateFixedInt(width, withoutpad, seedFlag);
            case "generateAmount":
                return com.dataGenerator.utils.FixedLengthGeneration.generateFixedAmount(width, withoutpad, seedFlag);
            case "generateFirstName":
                return com.dataGenerator.utils.TextGeneration.generateFirstName(first_names, seedFlag, random);
            case "generateLastName":
                return com.dataGenerator.utils.TextGeneration.generateLastName(last_names, seedFlag, random);
            case "generateFullName":
                return com.dataGenerator.utils.TextGeneration.generateFullName(first_names, last_names, seedFlag, random);
            case "generateDate":
                return com.dataGenerator.utils.DateGeneration.generateDate(min,max,pattern, seedFlag, random);
            case "generateBoolean":
                return com.dataGenerator.utils.OtherGeneration.generateBoolean(pattern, seedFlag, random);
            case "generateCurrency":
                return com.dataGenerator.utils.OtherGeneration.generateCurrency(pattern, currency, seedFlag, random);
            case "generateString":
                return com.dataGenerator.utils.TextGeneration.generateString(pattern, seedFlag, random);
            case "generatePositiveAmount":
                return com.dataGenerator.utils.FixedLengthGeneration.generateFixedPositiveAmount(width, withoutpad, seedFlag);
            case "generateConstantValue":
                return com.dataGenerator.utils.OtherGeneration.generateConstantValue(pattern);
            default:
                return null;
        }
    }

    public String generateFooter(String methodType, int width){
        switch (methodType){
            case "countOfRecords":
                return StringUtils.rightPad("Row Count : "+ConfigurationReaderUtils.getProperty("numberOfRecords"),width," ");
            default:
                return "Invalid name in footer generation";
        }
    }
}

