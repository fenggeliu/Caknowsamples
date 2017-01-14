package com.caknow.customer.util.net.payment;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by junu on 1/13/2017.
 */

public class AddPaymentRequest {
    private String cardToken;

    public AddPaymentRequest(String cardToken){
        this.cardToken = cardToken;
    }



    public static String getJsonString(AddPaymentRequest payload){
        Gson gson = new Gson();

        return gson.toJson(payload);
    }

    public static RequestBody getRequestBody(AddPaymentRequest request){
        return RequestBody.create(MediaType.parse("application/json"), getJsonString(request));
    }
}
