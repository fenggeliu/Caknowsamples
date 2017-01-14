package com.caknow.customer.util.net.auth;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by junu on 12/31/16.
 */

public interface AuthenticationAPI {

    @POST("/consumer")
    Call<AuthenticationResponse> login(@Body RequestBody postBody);

    @POST("consumer/forgetPassword")
    Call<Response> forgotPassword(@Body RequestBody emailBody);
}
