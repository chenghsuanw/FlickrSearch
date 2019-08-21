package com.example.flickrsearch;

public class Photo {
    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String title;
    private String isPublic;
    private String isFriend;
    private String isFamily;

    public Photo(String id, String owner, String secret, String server, String farm, String title, String isPublic, String isFriend, String isFamily) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.isPublic = isPublic;
        this.isFriend = isFriend;
        this.isFamily = isFamily;
    }

    public String getTitle() {
        return title;
    }

    public String getOwner() {
        return owner;
    }

    public String getURL() {
        return "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
    }
}
