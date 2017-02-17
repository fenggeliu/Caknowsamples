package com.caknow.customer.util.net.service;

import com.caknow.customer.util.net.BaseResponse;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by junu on 1/11/2017.
 */

public interface ServiceAPI {

    @GET("/consumer/serviceCatagory")
    Call<ServiceTypeResponse> getServiceTypeList(@Query("type") int typeId, @Query("parentId") String catagoryId);

    @GET("consumer/serviceRequests/{id}")
    Call<GetQuotesResponse> getQuotes(@Path("id") String id);

    @PUT("consumer/serviceRequest")
    Call<ServiceRequestResponse> submitNewServiceRequest(@Body RequestBody newService);

    @GET("/consumer/serviceRequests/quotes/current")
    Call<GetQuotesByServiceId> getQuotesForId(@Query("serviceRequestId") String serviceRequestId);

    @POST("/consumer/serviceRequest/cancel")
    Call<BaseResponse> cancelServiceByRequestId(@Body RequestBody requestId);

    @PATCH("/consumer/vehicle/serviceRequest")
    Call<BaseResponse> makeAppointment(@Body RequestBody appointmentRequest);
}
