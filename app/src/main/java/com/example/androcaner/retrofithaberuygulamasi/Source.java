package com.example.androcaner.retrofithaberuygulamasi;

public class Source {

    private String name;
    private int totalResults;

    public Source(String name, int totalResults) {
        this.name = name;
        this.totalResults = totalResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
