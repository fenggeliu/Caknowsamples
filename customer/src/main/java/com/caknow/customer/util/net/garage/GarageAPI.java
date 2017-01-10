package com.caknow.customer.util.net.garage;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by junu on 12/31/16.
 */

public interface GarageAPI {

    @GET("/consumer/vehicles")
    Call<GarageResponse> getVehicles();

    @PUT("/consumer/vehicles")
    Call<AddVehicleResponse> addVehicle(@Body RequestBody newVehicle);

    @DELETE("/consume/vehicles/{id}")
    Call<GarageResponse> deleteVehicle(@Path("id") String vehicleId);

    @GET("/client/mmy")
    Call<MMYResponse> getMMY();
}
