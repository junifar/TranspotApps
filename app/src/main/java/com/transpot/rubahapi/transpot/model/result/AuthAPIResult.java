package com.transpot.rubahapi.transpot.model.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.transpot.rubahapi.transpot.model.info.AuthAPIInfo;

/**
 * Created by prasetia on 8/9/2016.
 */
public class AuthAPIResult {
    @SerializedName("authAPIInfo")
    @Expose
    private AuthAPIInfo authAPIInfo;

    public AuthAPIInfo getAuthAPIInfo() {
        return authAPIInfo;
    }

    public void setAuthAPIInfo(AuthAPIInfo authAPIInfo) {
        this.authAPIInfo = authAPIInfo;
    }
}
