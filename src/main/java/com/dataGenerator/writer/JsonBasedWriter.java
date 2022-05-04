package com.dataGenerator.writer;

import com.dataGenerator.utils.RouterFunction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmif.common.regex.Generex;
import com.dataGenerator.json.FileConfigurationData;
import com.dataGenerator.json.RecordConfig;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class JsonBasedWriter implements RecordWriter {
    RouterFunction routerFunction = new RouterFunction();
    private static String generateFileName(String regex) {
        Generex generex = new Generex(regex);
        if(regex.contains("$Dae")) {

            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
            return generex.random().replace("$Date", ft.format(date)) + ".csv";
        }
        else
            return generex.random() + ".json";
    }

    private JSONObject jsonRecord = new JSONObject();
    private JSONArray jsonArray = new JSONArray();
    private ObjectMapper oMapper = new ObjectMapper();
    @Override
    public void writeFiles(com.dataGenerator.writer.RecordWriterConfig recordWriterConfig) {
        generateJsonFiles(recordWriterConfig);
    }

    public void generateJsonFiles(com.dataGenerator.writer.RecordWriterConfig recordWriterConfig) {

        try {
            File jsonFileName = new File(recordWriterConfig.getJsonFileName());
            FileInputStream fStream = new FileInputStream(jsonFileName);
            FileConfigurationData fileConfigurationData = oMapper.readValue(fStream, FileConfigurationData.class);
            //Generating the FileName
            String fileName = generateFileName(fileConfigurationData.getFileName());
            //Creating Empty Files
            FileWriter fileWriter = new FileWriter(recordWriterConfig.getDestinationFilePath() + "/" + fileName);
            //Initializing Helper function class
            String seedColumn = fileConfigurationData.getSeedColumns()+"".trim();
            List<String> columnList;
            if (seedColumn.equalsIgnoreCase("All") || seedColumn.equalsIgnoreCase("null"))
                columnList = Arrays.asList(fileConfigurationData.getHeaders().trim().split(","));
            else
                columnList = Arrays.asList(seedColumn.split(","));
            long seedVal= fileConfigurationData.getSeedVal();
            if(seedVal == 0){
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("MMddhhmmss");
                seedVal = Long.parseLong(ft.format(date))*10+new Random().nextInt(9);
            }

            Random random = new Random(seedVal);
            for (int i = 1; i <= recordWriterConfig.getNumberOfRecords(); i++) {
                for (RecordConfig recordConfig : fileConfigurationData.getRecordConfig()) {
//                    String columnData = routerFunction.getValue(recordConfig.getValue(), recordConfig.getWithoutpad(),
//                            recordConfig.getWidth(), recordConfig.getPattern(),recordConfig.getMin(),
//                            recordConfig.getMax(), random, columnList.contains(recordConfig.getName()) || seedColumn.equalsIgnoreCase("All"));
                    jsonRecord.put(recordConfig.getName(),columnData);
                }
                    jsonArray.put(jsonRecord);
                jsonRecord = new JSONObject();
                if ((i % 10000) == 0 || (i % recordWriterConfig.getNumberOfRecords()) == 0) {
                    fileWriter.append(jsonArray.toString());
                    jsonArray = new JSONArray();
                }
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
}
