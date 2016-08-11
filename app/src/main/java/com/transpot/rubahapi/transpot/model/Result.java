package com.transpot.rubahapi.transpot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prasetia on 8/9/2016.
 * NOTE : For Sample Test
 */
public class Result {
    @SerializedName("info")
    @Expose
    private Info info;

    /**
     *
     * @return
     * The info
     */
    public Info getInfo() {
        return info;
    }

    /**
     *
     * @param info
     * The info
     */
    public void setInfo(Info info) {
        this.info = info;
    }
}
