package com.stupefy.stupefysubscription.httpclient;

import com.stupefy.stupefysubscription.constants.APIKey;
import com.stupefy.stupefysubscription.httpclient.Request.RespondRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {
    private final Apiservice apiservice;
    private String BASE_URL = "http://localhost:8080/";

    public HttpClient(String _BASE_URL) {
        BASE_URL = _BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiservice = retrofit.create(Apiservice.class);
    }

    public void putRespond(String dir, String status) {
        RespondRequest respond = new RespondRequest(status, APIKey.APP);
        Call<Boolean> call = apiservice.putRespond(dir, respond);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    System.out.println("Success");
                } else {
                    System.out.println("invalid");
                    System.out.println(dir);
                    System.out.println(status);
                    System.out.println(call.request());
                    System.out.println(response.code());
                    System.out.println(response.body());
                    System.out.println(response.headers());
                    System.out.println(response.message());
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
