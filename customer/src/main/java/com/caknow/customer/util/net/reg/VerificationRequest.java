package com.caknow.customer.util.net.reg;

import com.google.gson.Gson;

/**
 * Created by junu on 1/6/17.
 */

public class VerificationRequest {
    String verificationCode;

    public VerificationRequest(String verificationCode){
        this.verificationCode = verificationCode;
    }

    public static String getJsonString(VerificationRequest request) {
        Gson gson = new Gson();

        return gson.toJson(request);
    }
}
