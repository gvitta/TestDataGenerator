package com.dataGenerator;

import com.dataGenerator.json.ConfigurationReaderUtils;
import com.dataGenerator.writer.FileType;
import com.dataGenerator.writer.RecordWriter;
import com.dataGenerator.writer.RecordWriterConfig;
import com.dataGenerator.writer.RecordWriterFactory;

import java.util.Properties;

public class RecordGenerator {

    public void generateRecords(String args[]) {

        Properties properties = ConfigurationReaderUtils.readConfiguration(args[0].trim());
        RecordWriter recordWriter = RecordWriterFactory.getInstance(properties.getProperty("fileType"));
        if (recordWriter == null) {
            throw new IllegalArgumentException("File Writer type not found");
        }

        RecordWriterConfig recordWriterConfig = new RecordWriterConfig();
        recordWriterConfig.setNumberOfRecords(Integer.parseInt(properties.getProperty("numberOfRecords")));
        recordWriterConfig.setFileType(FileType.valueOf(properties.getProperty("fileType")));
        recordWriterConfig.setJsonFileName(properties.getProperty("schemaPath") + args[1]);
        if(args.length==3)
            recordWriterConfig.setSeedValue(args[2]);

        long startTime = System.currentTimeMillis();
        recordWriter.writeFiles(recordWriterConfig);
        long endTime = System.currentTimeMillis();
        System.out.println("Took " + (endTime - startTime) + " ms");
        }
}
