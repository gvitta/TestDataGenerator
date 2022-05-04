package com.dataGenerator.writer;

import com.dataGenerator.utils.GenerateData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmif.common.regex.Generex;
import com.dataGenerator.json.ConfigurationReaderUtils;
import com.dataGenerator.json.FileConfigurationData;
import com.dataGenerator.json.RecordConfig;
import com.dataGenerator.json.TrailerConfig;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CsvBasedWriter implements RecordWriter {
    private static String generateFileName(String regex, long seedVal) {
        Generex generex = new Generex(regex);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
        return generex.random().replace("$Date", ft.format(date)).replace("$seed",seedVal+"") + ".csv";
    }

    private StringBuilder rowData = new StringBuilder();
    private ObjectMapper oMapper = new ObjectMapper();

    @Override
    public void writeFiles(RecordWriterConfig recordWriterConfig) {
        generateCsvFiles(recordWriterConfig);
    }

    public void generateCsvFiles(RecordWriterConfig recordWriterConfig) {

        try {
            File jsonFileName = new File(recordWriterConfig.getJsonFileName());
            FileInputStream fStream = new FileInputStream(jsonFileName);
            FileConfigurationData fileConfigurationData = oMapper.readValue(fStream, FileConfigurationData.class);

            String delim = fileConfigurationData.getDelimiter();
            if (delim == null || delim == "" || delim.isEmpty() || delim == "null")
                delim = ",";
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
            //Preparing Header for the file
            fileWriter.write(fileConfigurationData.getHeaders());

            String seedColumn = fileConfigurationData.getSeedColumns()+"".trim();
            List<String> columnList;
            if (seedColumn.equalsIgnoreCase("All") || seedColumn.equalsIgnoreCase("null"))
                columnList = Arrays.asList(fileConfigurationData.getHeaders().trim().split(delim));
            else
                columnList = Arrays.asList(seedColumn.split(delim));

            int div = 3;
            for (int i = 1; i <= recordWriterConfig.getNumberOfRecords(); i++) {
                for (RecordConfig recordConfig : fileConfigurationData.getRecordConfig()) {
                    String columnData;
                    if(recordConfig.getNull_flag()==false){
                         columnData = generateData.generate(recordConfig.getValue(), recordConfig.getWidth(), recordConfig.getPattern(),recordConfig.getMin(),
                                recordConfig.getMax(), columnList.contains(recordConfig.getName()) || seedColumn.equalsIgnoreCase("All"));
                    } else {
                        int val = ((int) (Math.random()*1000));

                        if (recordConfig.getNull_percent() > 0) {
                            div = (int) (100/ recordConfig.getNull_percent());
                            val = i;
                        }
                        if (val%div == 0)
                            columnData = null;
                        else
                            columnData = generateData.generate(recordConfig.getValue(), recordConfig.getWidth(), recordConfig.getPattern(),recordConfig.getMin(),
                                    recordConfig.getMax(), columnList.contains(recordConfig.getName()) || seedColumn.equalsIgnoreCase("All"));
                    }
                    rowData.append(columnData).append(delim);
                }
                rowData.deleteCharAt(rowData.length() - 1);
                rowData.append("\n");
                if ((i % 10000) == 0 || (i % recordWriterConfig.getNumberOfRecords()) == 0) {
                    fileWriter.append(rowData.toString());
                    rowData = new StringBuilder();
                }
            }
            if(fileConfigurationData.getTrailer()!=null) {
                for (TrailerConfig trailerConfig : fileConfigurationData.getTrailer()) {
                    rowData.append(generateData.generateFooter(trailerConfig.getName(), trailerConfig.getWidth())).append(delim);
                }
                rowData.deleteCharAt(rowData.length() - 1);
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
}
