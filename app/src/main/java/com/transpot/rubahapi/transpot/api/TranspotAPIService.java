package com.transpot.rubahapi.transpot.api;

import com.transpot.rubahapi.transpot.model.serialized.SerializedToken;

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
    Call<SerializedToken> getToken(@Field("username") String username, @Field("password") String password);

    //NOTE : Sample for call json data
    @FormUrlEncoded
    @POST("api-token-auth/")
    Call<ResponseBody> getResultAsJSON(@Field("username") String username, @Field("password") String password);

    //NOTE : For sample test
    @GET("car")
    Call<ResponseBody> getResultCar();
}
