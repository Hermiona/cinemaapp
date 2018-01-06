package com.example.cinemaapp;

/**
 * Created by ASUS on 06.01.2018.
 */

public class MovieModel {
    private int id;
    private String title;
    private String imgUrl;

    public MovieModel(int id, String title, String imgUrl){
        this.id=id;
        this.title=title;
        this.imgUrl=imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

