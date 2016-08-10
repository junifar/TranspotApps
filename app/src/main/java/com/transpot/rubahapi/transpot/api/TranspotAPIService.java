package com.transpot.rubahapi.transpot.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by prasetia on 8/9/2016.
 */
public interface TranspotAPIService {
    @FormUrlEncoded
    @POST("api-token-auth/")
    Call<ResponseBody> getResultAsJSON(@Field("username") String username, @Field("password") String password);

    @GET("car")
    Call<ResponseBody> getResultCar();
}
