package com.caknow.customer.util.net.settings;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by junu on 1/13/2017.
 */

public class UpdatePasswordRequest {

    private final String password;
    private final String newPassword;

    public UpdatePasswordRequest(final String password, final String newPassword){
        this.password  = password;
        this.newPassword = newPassword;
    }
    private static String getJsonString(UpdatePasswordRequest payload){
        Gson gson = new Gson();
        return gson.toJson(payload);
    }

    public static RequestBody getRequestBody(UpdatePasswordRequest request){
        return RequestBody.create(MediaType.parse("application/json"), getJsonString(request));
    }
}
