package com.caknow.customer.util.net.garage;

import com.caknow.customer.util.net.AuthenticationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by junu on 12/31/16.
 */

public interface GarageAPI {

    @GET("/consumer/vehicles")
    Call<GarageResponse> getVehicles();

}
