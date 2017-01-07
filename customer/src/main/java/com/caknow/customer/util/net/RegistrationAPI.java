package com.caknow.customer.util.net;

import com.caknow.customer.util.net.auth.AuthenticationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

/**
 * Created by junu on 12/31/16.
 */

public interface RegistrationAPI {

    String ENDPOINT = "http://dev.caknow.com";

    /**
     * Create a new consumer
     * @param postBody
     * @return
     */
    @PUT("/consumer")
    Call<AuthenticationResponse> login(@Body RequestBody postBody);



}
