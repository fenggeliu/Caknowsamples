package com.caknow.customer.util.net.transaction;

import com.caknow.customer.util.net.payment.AddPaymentRequest;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PaymentRequest {

    @SerializedName("serviceRequestId")
    @Expose
    private String serviceRequestId;
    @SerializedName("quoteId")
    @Expose
    private String quoteId;
    @SerializedName("paymentProvider")
    @Expose
    private String paymentProvider;
    @SerializedName("paymentSource")
    @Expose
    private String paymentSource;

    /**
     * No args constructor for use in serialization
     */
    public PaymentRequest() {
    }

    /**
     * @param serviceRequestId
     * @param quoteId
     * @param paymentProvider
     * @param paymentSource
     */
    public PaymentRequest(String serviceRequestId, String quoteId, String paymentProvider, String paymentSource) {
        super();
        this.serviceRequestId = serviceRequestId;
        this.quoteId = quoteId;
        this.paymentProvider = paymentProvider;
        this.paymentSource = paymentSource;
    }

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getQuoteId() {
        return quoteId;
    }


    public String getPaymentProvider() {
        return paymentProvider;
    }


    public String getPaymentSource() {
        return paymentSource;
    }

    public static String getJsonString(PaymentRequest payload){
        Gson gson = new Gson();

        return gson.toJson(payload);
    }

    public static RequestBody getRequestBody(PaymentRequest request){
        return RequestBody.create(MediaType.parse("application/json"), getJsonString(request));
    }

}