package com.example.flickrsearch;

import java.util.List;

public class Photos {
    private List<Photo> photo;

    public Photos(List<Photo> photo) {
        this.photo = photo;
    }

    public List<Photo> getPhoto() {
        return photo;
    }
}
