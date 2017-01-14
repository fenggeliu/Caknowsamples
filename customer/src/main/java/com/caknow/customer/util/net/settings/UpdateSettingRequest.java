package com.caknow.customer.util.net.settings;

import com.caknow.customer.util.net.payment.AddPaymentRequest;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by junu on 1/13/2017.
 */

public class UpdateSettingRequest {

    private String phone;

    public UpdateSettingRequest(final String phoneNumber){
        this.phone = phoneNumber;
    }
    public static String getJsonString(UpdateSettingRequest payload){
        Gson gson = new Gson();

        return gson.toJson(payload);
    }

    public static RequestBody getRequestBody(UpdateSettingRequest request){
        return RequestBody.create(MediaType.parse("application/json"), getJsonString(request));
    }
}
