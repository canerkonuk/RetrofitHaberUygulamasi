package com.example.androcaner.retrofithaberuygulamasi;

import java.util.List;

public class News {

    private String status;
    private List<Article> articles;

    public News(String status, List<Article> articles) {
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
