package com.seanweng.drama.dataBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 戲劇List
 */
public class Dramas {

    @SerializedName("data")
    private List<Drama> data;

    public List<Drama> getData() {
        return data;
    }

    public void setData(List<Drama> data) {
        this.data = data;
    }

}
