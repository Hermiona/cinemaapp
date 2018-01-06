package com.example.cinemaapp;

/**
 * Created by ASUS on 06.01.2018.
 */

public class CinemaModel {
    private int id;
    private String name;

    public CinemaModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
