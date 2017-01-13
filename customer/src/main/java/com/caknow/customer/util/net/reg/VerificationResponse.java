package com.caknow.customer.util.net.reg;

/**
 * Created by junu on 1/6/17.
 */

public class VerificationResponse {
    boolean success;
    private String _id;
    private String message;
    private VerificationPayload payload;

    public VerificationPayload getPayload() {
        return this.payload;
    }

}
