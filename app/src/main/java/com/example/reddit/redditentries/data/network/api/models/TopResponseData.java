package com.example.reddit.redditentries.data.network.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Diego Volantino on 18/3/18.
 */


public class TopResponseData {
    @SerializedName("children")
    private ArrayList<Children> childrenList;

    public ArrayList<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(ArrayList<Children> childrenList) {
        this.childrenList = childrenList;
    }
}
