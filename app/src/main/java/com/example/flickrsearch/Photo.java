package com.example.flickrsearch;

import java.io.Serializable;

public class Photo implements Serializable {
    private String id;
    private String secret;
    private String server;
    private String farm;


    public Photo(String id, String secret, String server, String farm) {
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.farm = farm;

    }

    public String getURL() {
        return "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
    }

    public String getId() {
        return id;
    }
}
