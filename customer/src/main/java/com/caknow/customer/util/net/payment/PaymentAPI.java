package com.caknow.customer.util.net.payment;

import com.caknow.customer.util.net.service.ServiceRequestResponse;
import com.caknow.customer.util.net.service.ServiceTypeResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by junu on 1/11/2017.
 */

public interface PaymentAPI {

    @PUT("consumer/stripePaymentMethods")
    Call<AddPaymentResponse> addPayment(@Body RequestBody newService);

    @GET("/consumer/stripe/customer")
    Call<GetPaymentsResponse> getPayments();

    @POST("/consumer/serviceRequests/quotes/accept")
    Call<ResponseBody> makePayment(@Body RequestBody body);

    @DELETE("/consumer/payment/{provider]/cards/{id}")
    Call<ResponseBody> deletePayments(@Path("provider") String provider, @Path("id") String cardId);
}
