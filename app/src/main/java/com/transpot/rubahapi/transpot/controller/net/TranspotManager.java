package com.transpot.rubahapi.transpot.controller.net;

import android.os.HandlerThread;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junifar on 01-Aug-16.
 */
public class TranspotManager {
    private static final String BASE_URL = "http://localhost:8000/member";

    private static final TranspotManager instance = new TranspotManager();

    public static TranspotManager getInstance(){ return instance; }

    private Handler mHandler;
    private TranspotService mTranspotService;
    private final OkHttpClient mClient;
    private final OkHttpClient mTranspot;

    public TranspotManager() {
        mTranspot = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        HandlerThread thread = new HandlerThread("Transpot Manager Thread");
        thread.start();

        mHandler = new Handler(thread.getLooper());

        CookieJar tempJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(okhttp3.HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };

        Gson gson = new GsonBuilder().setLenient().create();

        mClient = new OkHttpClient.Builder()
                .cookieJar(tempJar)
                .addInterceptor(new NetworkRequestLoggingInterceptor())
                .build();

        mTranspotService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mClient)
                .build()
                .create(TranspotService.class);

    }
}
