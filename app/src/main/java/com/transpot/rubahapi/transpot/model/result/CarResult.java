package com.transpot.rubahapi.transpot.model.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.transpot.rubahapi.transpot.model.info.AuthAPIInfo;
import com.transpot.rubahapi.transpot.model.info.CarInfo;

/**
 * Created by prasetia on 8/10/2016.
 */
public class CarResult {
    @SerializedName("CarInfo")
    @Expose
    private CarInfo carInfo;

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }
}
