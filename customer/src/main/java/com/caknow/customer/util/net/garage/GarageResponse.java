package com.caknow.customer.util.net.garage;

/**
 * Created by junu on 12/31/16.
 */

public class GarageResponse {
    boolean success;
    private  String message;
    private  GaragePayload payload;

    public GaragePayload getGaragePayload(){
        return this.payload;
    }
}