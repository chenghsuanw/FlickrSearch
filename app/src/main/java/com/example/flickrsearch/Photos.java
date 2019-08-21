package com.example.flickrsearch;

import java.util.List;

public class Photos {
    private Integer page;
    private Integer pages;
    private Integer perpage;
    private String total;
    private List<Photo> photo;

    public Photos(Integer page, Integer pages, Integer perpage, String total, List<Photo> photo) {
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
        this.photo = photo;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public String getTotal() {
        return total;
    }
}
