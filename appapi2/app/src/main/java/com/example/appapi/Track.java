package com.example.appapi;

import com.google.gson.annotations.SerializedName;

public class Track {

    String id;
    String title;
    String singer;
    static int lastId;

    @SerializedName("body")
    String comment;

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Track.lastId = lastId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Track [id="+id+", title=" + title + ", singer=" + singer +"]";
    }

    public Track() {
    }

    public Track(String title, String singer) {
        this();
        this.setSinger(singer);
        this.setTitle(title);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }


}
