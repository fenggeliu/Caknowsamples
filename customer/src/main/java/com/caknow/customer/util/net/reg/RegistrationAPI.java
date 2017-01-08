package com.caknow.customer.util.net.reg;

import com.caknow.customer.util.net.auth.AuthenticationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by junu on 12/31/16.
 */

public interface RegistrationAPI {

    @PUT("/consumer")
    Call<AuthenticationResponse> register(@Body RequestBody postBody);

    @POST("/consumer/validate")
    Call<VerificationResponse> verify(@Body RequestBody postBody);

}
