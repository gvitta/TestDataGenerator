package com.dataGenerator.json;

import java.util.List;

public class FileConfigurationData {

    private String fileType;
    private String fileSubType;
    private String headers;
    private String delimiter;
    private List<com.dataGenerator.json.RecordConfig> recordConfig;
    private List<com.dataGenerator.json.TrailerConfig> trailer;
    private long seedVal;
    private String seedColumns;
    private String fileName;
   

    public void setSeedVal(long seed) {
        this.seedVal=seed;
    }

    public long getSeedVal() {
        return seedVal;
    }

    public void setFileType(String str) {
        this.fileType=str;
    }

    public String getFileType() {
        return fileType;
    }

    public void setDelimiter(String delim) {
        this.delimiter=delim;
    }

    public String getDelimiter(){
        return this.delimiter;
    }

    public String getSeedColumns() {
        return seedColumns;
    }

    public void setSeedColumns(String seedColumn) {
        this.seedColumns = seedColumn;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }
    
    public List<com.dataGenerator.json.RecordConfig> getRecordConfig() {
        return recordConfig;
    }

    public void setRecordConfig(List<RecordConfig> recordConfig) {
        this.recordConfig = recordConfig;
    }

    public List<TrailerConfig> getTrailer() {
        return trailer;
    }

    public void setTrailer(List<TrailerConfig> trailer) {
        this.trailer = trailer;
    }

    public String getFileSubType() {
        return fileSubType;
    }

    public void setFileSubType(String fileSubType) {
        this.fileSubType = fileSubType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
