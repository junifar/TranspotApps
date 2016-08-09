package com.transpot.rubahapi.transpot.controller.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by junifar on 02-Aug-16.
 */
public interface TranspotService {

    @Headers("User Agent : RubahApi")
    @GET("http://localhost:8000/members")
    Call<LoginValues> getLoginValues();

    @Headers("User Agent : RubahApi")
    @POST
    Call<LoginResponse> login(@Url String url);

    @Headers("User Agent : RubahApi")
    @POST
    Call<ResponseBody> requestToken(@Url String url);

    @POST("http://localhost:8000/api-token-auth/")
    Call<LoginResponse> apiTokenAuth();

    class LoginValues {

        private String lt;
        private String execution;

        public LoginValues(){}

        public String getExecution() {
            return execution;
        }

        public String getLt() {
            return lt;
        }
    }

    class LoginResponse{
        public LoginResponse(){}
    }

    class LoginError{
        private String[] errors;
    }

}
