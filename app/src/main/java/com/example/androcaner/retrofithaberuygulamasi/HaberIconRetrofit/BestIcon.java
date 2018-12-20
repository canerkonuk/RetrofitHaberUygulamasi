package com.example.androcaner.retrofithaberuygulamasi.HaberIconRetrofit;

import java.util.List;

public class BestIcon {

    private String url;
    private List<Icon> icons;

    public BestIcon(String url, List<Icon> icons) {
        this.url = url;
        this.icons = icons;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }
}
