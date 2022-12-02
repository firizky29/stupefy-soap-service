package com.stupefy.stupefysubscription.httpclient;

import com.stupefy.stupefysubscription.httpclient.Request.RespondRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Apiservice {
    @PUT("{dir}")
    Call<Boolean> putRespond(@Path(value="dir",encoded=true) String dir, @Body RespondRequest respond);
}
