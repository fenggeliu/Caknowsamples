package com.caknow.customer.util.net;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by junu on 12/31/16.
 */

public interface AuthenticationAPI {

    String ENDPOINT = "http://dev.caknow.com";

    @POST("/consumer")
    Call<AuthenticationResponse> login(@Body RequestBody postBody);

}
