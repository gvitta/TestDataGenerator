package com.dataGenerator.writer;

public class RecordWriterConfig {
    private int numberOfRecords;
    private FileType fileType;
    private long seedValue;
    private String jsonFileName;
    private String destinationFilePath;


    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    public long getSeedValue(){ return seedValue; }

    public void setSeedValue(String seed){
        this.seedValue = Long.parseLong(seed);
    }


    public String getDestinationFilePath() {
        return this.destinationFilePath;
    }

    public void setDestinationFilePath(String destinationFilePath) {
        this.destinationFilePath = destinationFilePath;
    }

}
