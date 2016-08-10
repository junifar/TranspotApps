package com.transpot.rubahapi.transpot.model.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prasetia on 8/10/2016.
 */
public class CarInfo {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("plat_no")
    @Expose
    private String plat_no;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlat_no() {
        return plat_no;
    }

    public void setPlat_no(String plat_no) {
        this.plat_no = plat_no;
    }
}
