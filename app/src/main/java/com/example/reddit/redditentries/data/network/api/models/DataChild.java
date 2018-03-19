package com.example.reddit.redditentries.data.network.api.models;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public class DataChild {
    private  String title,author,thumbnail;
    private int num_comments;
    private long created_utc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public String getThumbnail() {

        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {

        this.thumbnail = thumbnail;
    }

    public int getNum_comments() {

        return num_comments;
    }

    public void setNum_comments(int num_comments) {

        this.num_comments = num_comments;
    }

    public long getCreated_utc() {

        return created_utc;
    }

    public void setCreated_utc(long created_utc) {
        this.created_utc = created_utc;
    }
}
