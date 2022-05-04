package com.dataGenerator.writer;

public final class RecordWriterFactory {
    public static RecordWriter getInstance(String writerType) {
        switch (writerType) {
            case "FIXEDLENGTH":
                return new FixedLengthBasedWriter();
            case "CSV":
                return new CsvBasedWriter();
            default:
                return null;

        }
    }
}
