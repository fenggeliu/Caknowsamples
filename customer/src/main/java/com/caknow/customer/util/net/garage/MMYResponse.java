package com.caknow.customer.util.net.garage;


/**
 * Created by junu on 12/31/16.
 */

public class MMYResponse {
    boolean success;
    String message;
    MMYPayload payload;

    public MMYPayload getPayload(){
        return this.payload;
    }
}
