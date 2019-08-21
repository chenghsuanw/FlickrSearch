package com.example.flickrsearch;

public class Data {
    private String stat;
    private Photos photos;

    public Data(String stat, Photos photos) {
        this.stat = stat;
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public Photos getPhotos() {
        return photos;
    }
}
