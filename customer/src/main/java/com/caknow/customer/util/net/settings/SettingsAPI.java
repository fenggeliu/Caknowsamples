package com.caknow.customer.util.net.settings;

import com.caknow.customer.util.net.auth.AuthenticationResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;

/**
 * Created by junu on 1/13/2017.
 */

public interface SettingsAPI {

    @PATCH("/consumer/resetPassword")
    Call<ResponseBody> updatePassword(@Body RequestBody body);

    @PATCH("/consumer")
    Call<ResponseBody> updateProfile(@Body RequestBody body);

    @GET("/consumer")
    Call<ConsumerInfoResponse> getConsumerInfo();
}
