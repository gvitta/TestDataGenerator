package com.dataGenerator.json;

public class RecordConfig {

    private String name;
    private String start;
    private int width;
    private String value;
    private int withoutpad;
    private String min;
    private String max;
    private String pattern;
    private boolean null_flag;
    private double null_percent;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWithoutpad() {
        return withoutpad;
    }

    public void setWithoutpad(int withoutpad) {
        this.withoutpad = withoutpad;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean getNull_flag() {
        return null_flag;
    }

    public void setNull_flag(boolean null_flg){ this.null_flag=null_flg;}

    public double getNull_percent(){return null_percent;}

    public void setNull_percent(double null_percent) {
        this.null_percent = null_percent;
    }
}
