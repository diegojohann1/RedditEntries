package com.example.reddit.redditentries.data.network.api.models;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public class Children {
    private String kind;
    private DataChild data;

    public String getKind() {

        return kind;
    }

    public void setKind(String kind) {

        this.kind = kind;
    }

    public DataChild getData() {

        return data;
    }

    public void setData(DataChild data) {

        this.data = data;
    }
}
