package com.example.androcaner.retrofithaberuygulamasi.HaberIconRetrofit;

public class Icon {

    private String url;
    private int width;
    private int height;
    private int bytes;
    private Object error;
    private String format;
    private String shalsum;


    public Icon(String url, int width, int height, int bytes, Object error, String format, String shalsum) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.bytes = bytes;
        this.error = error;
        this.format = format;
        this.shalsum = shalsum;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getShalsum() {
        return shalsum;
    }

    public void setShalsum(String shalsum) {
        this.shalsum = shalsum;
    }
}
