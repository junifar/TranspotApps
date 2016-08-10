package com.transpot.rubahapi.transpot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.transpot.rubahapi.transpot.api.TranspotAPIService;
import com.transpot.rubahapi.transpot.api.UserAPIService;
import com.transpot.rubahapi.transpot.util.Const;
import com.transpot.rubahapi.transpot.util.TranspotConst;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private View mProgressView;

    private Retrofit retrofit;
    private Retrofit retrofitTranspot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeRetrofit();

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button signInButton = (Button) findViewById(R.id.email_sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLoginForm();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        getTokenInterceptor(httpClient,null);
        getAuthInterceptor(httpClient);

        OkHttpClient client = httpClient.build();
        retrofitTranspot = new Retrofit.Builder()
                .baseUrl(TranspotConst.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private OkHttpClient.Builder getAuthInterceptor(OkHttpClient.Builder httpClient){
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("username","admin")
                        .addQueryParameter("password","janganlagi1")
                        .build();

                Request.Builder requestBuilder = original.newBuilder().url(url);

                Request request = requestBuilder.build();
                return  chain.proceed(request);
            }
        });

        return  httpClient;
    }

    private OkHttpClient.Builder getTokenInterceptor(OkHttpClient.Builder httpClient, String Token){
        if (Token == null){
            Token = "59463fb9d779338793cca456fd22ef8d05acbeea";
        }
        final String finalToken = Token;
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","Token "+ finalToken)
                        .method(original.method(),original.body())
                        .build();

                return  chain.proceed(request);
            }
        });
        return httpClient;
    }

    private void validateLoginForm() {

        mUsernameView.setError(null);
        mPasswordView.setError(null);

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)){
            mPasswordView.setError("Field Required");
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)){
            mUsernameView.setError("Field Required");
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel){
            focusView.requestFocus();
        } else {
            showProgress(true);
        }
        triggerAutoLogin();
    }

    private void triggerAutoLogin(){
//        mUsernameView.setText("Test Dulu");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doStuff();
            }
        },3000);
    }

    private void doStuff(){
        mUsernameView.setText("Test First");
        Toast.makeText(this, "Delayed Toast!", Toast.LENGTH_SHORT).show();
//        getDataAsJSON();
        getTokenAsJson();
//        getCarJsonData();
        showProgress(false);
    }

    private void getTokenAsJson(){
        TranspotAPIService apiService = retrofitTranspot.create(TranspotAPIService.class);

        Call<ResponseBody>  result = apiService.getResultAsJSON("admin", "janganlagi1");
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                System.out.println("Response status code: " + response.code());
//                Toast.makeText(LoginActivity.this," Response version " + response.code(), Toast.LENGTH_SHORT).show();
                try {
                    Toast.makeText(LoginActivity.this," Response version " + response.body().string(), Toast.LENGTH_SHORT).show();
//                    mUsernameView.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(LoginActivity.this," Error Response version " + t.toString(), Toast.LENGTH_SHORT).show();
//                mUsernameView.setText(t.toString());
                t.printStackTrace();
            }
        });
    }

    private void getCarJsonData(){
        TranspotAPIService apiService = retrofitTranspot.create(TranspotAPIService.class);
        Call<ResponseBody> result = apiService.getResultCar();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(LoginActivity.this," response version "+response.body().string(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mUsernameView.setText(t.toString());
                t.printStackTrace();
            }
        });
    }

    private void getDataAsJSON(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<ResponseBody> result = apiService.getResultAsJSON();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                dialog.dismiss();
                try {
                    Toast.makeText(LoginActivity.this," response version "+response.body().string(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){

            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(
                    new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {}

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {}

                        @Override
                        public void onAnimationRepeat(Animator animator) {}
                    });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(
                    new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });
        }else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
