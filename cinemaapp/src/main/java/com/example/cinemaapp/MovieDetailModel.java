package com.example.cinemaapp;

/**
 * Created by ASUS on 06.01.2018.
 */

public class MovieDetailModel {
    private int id;
    private  String title;
    private  String imgUrl;
    private String genre;
    private  String description;

    public MovieDetailModel(int id, String title, String imgUrl, String genre, String description) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
        this.genre = genre;
        this.description = description;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
