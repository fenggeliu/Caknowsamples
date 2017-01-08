package com.caknow.customer.util.net.reg;

import com.caknow.customer.util.net.auth.AuthenticationPayload;

/**
 * Created by junu on 1/6/17.
 */

public class VerificationResponse {
    private String _id;
    boolean success;
    private  String message;
    private VerificationPayload payload;

    public VerificationPayload getPayload(){
        return this.payload;
    }

}
