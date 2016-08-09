package com.transpot.rubahapi.transpot.api;

import com.transpot.rubahapi.transpot.model.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by prasetia on 8/9/2016.
 */
public interface UserAPIService {
    @GET("api")
    Call<Result> getResultInfo();

    @GET("api")
    Call<ResponseBody> getResultAsJSON();
}
