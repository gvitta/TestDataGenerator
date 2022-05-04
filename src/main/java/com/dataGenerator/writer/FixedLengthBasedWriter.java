package com.dataGenerator.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmif.common.regex.Generex;
import com.dataGenerator.json.ConfigurationReaderUtils;
import com.dataGenerator.json.FileConfigurationData;
import com.dataGenerator.json.RecordConfig;
import com.dataGenerator.json.TrailerConfig;
import com.dataGenerator.utils.GenerateData;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FixedLengthBasedWriter implements RecordWriter {
    private List<String> footerCount = new ArrayList<>();
    private LinkedHashSet tempAccountNumber = new LinkedHashSet();
    private StringBuilder rowData = new StringBuilder();
    private ObjectMapper oMapper = new ObjectMapper();
    private static String generateFileName(String regex, long seedVal) {
        Generex generex = new Generex(regex);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
        return generex.random().replace("$Date", ft.format(date)).replace("$seed",seedVal+"") + ".csv";
    }

    @Override
    public void writeFiles(RecordWriterConfig recordWriterConfig) {
        generateFixedLengthFiles(recordWriterConfig);
    }

    public void generateFixedLengthFiles(RecordWriterConfig recordWriterConfig) {
        try {
            File jsonFileName = new File(recordWriterConfig.getJsonFileName());
            FileInputStream fStream = new FileInputStream(jsonFileName);
            FileConfigurationData fileConfigurationData = oMapper.readValue(fStream, FileConfigurationData.class);
            long seedVal;
            if(recordWriterConfig.getSeedValue()>0)
                seedVal = recordWriterConfig.getSeedValue();
            else
                seedVal = fileConfigurationData.getSeedVal();
            if(seedVal == 0){
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("MMddhhmmss");
                seedVal = Long.parseLong(ft.format(date))*10+new Random().nextInt(9);
            }
            GenerateData generateData = new GenerateData(seedVal);

            //Generating the FileName
            String fileName = generateFileName(ConfigurationReaderUtils.getProperty("fileName"),seedVal);
            //Creating Empty Files
            FileWriter fileWriter = new FileWriter(ConfigurationReaderUtils.getProperty("destinationFilePath") + "/" + fileName);

            String seedColumn = fileConfigurationData.getSeedColumns()+"".trim();
            List<String> columnList;
            if (seedColumn.equalsIgnoreCase("All") || seedColumn.equalsIgnoreCase("null"))
                columnList = Arrays.asList(fileConfigurationData.getHeaders().trim().split(","));
            else
                columnList = Arrays.asList(seedColumn.split(","));


            //Writing Header for the file
            fileWriter.write(fileConfigurationData.getHeaders());
            for (int i = 1; i <= recordWriterConfig.getNumberOfRecords(); i++) {
                for (RecordConfig recordConfig : fileConfigurationData.getRecordConfig()) {
                    String columnData = generateData.generateFixedLength(recordConfig.getValue(), recordConfig.getWithoutpad(), recordConfig.getWidth(),
                            recordConfig.getPattern(),recordConfig.getMin(),recordConfig.getMax(),
                            columnList.contains(recordConfig.getName()) || seedColumn.equalsIgnoreCase("All"));

                    countFooterAttributes(columnData, recordConfig.getName());
                    rowData.append(columnData);
                }
                rowData.append("\n");
                if ((i % 10000) == 0 || i == recordWriterConfig.getNumberOfRecords()) {
                    fileWriter.append(rowData.toString());
                    fileWriter.flush();
                    rowData = new StringBuilder();
                }
            }
            fileWriter.append("\n99").append(generateFooterAttributes(fileConfigurationData.getFileSubType(), recordWriterConfig.getNumberOfRecords()).toString()).append("\n");
            if(fileConfigurationData.getTrailer()!=null) {
                for (TrailerConfig trailerConfig : fileConfigurationData.getTrailer()) {
                    rowData.append(generateData.generateFooter(trailerConfig.getName(), trailerConfig.getWidth()));
                }
                fileWriter.append(rowData.toString());
            }
            System.out.println("Total " + recordWriterConfig.getNumberOfRecords() + " records written to file:" + fileName);
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder generateFooterAttributes(String fileSubType, int numberOfRecords) {
        int count1 = 0;
        int count2 = 0;
        StringBuilder footerValue = new StringBuilder();
        for (String s : footerCount) {
            if (s.equals("C"))
                count1++;
            else
                count2++;
        }
        footerValue.append(StringUtils.leftPad(String.valueOf(numberOfRecords), 10, "0"));
        if(fileSubType.equals("Undischarged")) {
            footerValue.append("0000000000");
            footerValue.append(StringUtils.leftPad(String.valueOf(tempAccountNumber.size()), 10, "0"));
            footerValue.append(StringUtils.leftPad(String.valueOf(count1), 10, "0"));
            footerValue.append(StringUtils.leftPad(String.valueOf(count2), 10, "0"));
            footerValue.append(StringUtils.leftPad("~", 170, "~"));
        }
        return footerValue;
    }

    private void countFooterAttributes(String columnValue, String columnName) {
        switch (columnName) {
            case "Debit_Credit_Indicator":
                footerCount.add(columnValue);
                break;
            case "Account_Number":
                tempAccountNumber.add(columnValue);
                break;
        }
    }

}
