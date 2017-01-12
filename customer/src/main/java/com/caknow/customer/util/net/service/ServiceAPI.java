package com.caknow.customer.util.net.service;

import com.caknow.customer.util.net.service.ServiceTypeResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by junu on 1/11/2017.
 */

public interface ServiceAPI {

    @GET("/consumer/serviceCatagory")
    Call<ServiceTypeResponse> getServiceTypeList(@Query("type") int typeId, @Query("parentId") String catagoryId);

    @PUT("consumer/serviceRequests")
    Call<ServiceRequestResponse> submitNewServiceRequest(@Body RequestBody newService);
}
