package com.caknow.customer.util.net.garage.addvehicle;

import com.caknow.customer.util.net.garage.GaragePayload;

/**
 * Created by junu on 12/31/16.
 */

public class AddVehicleResponse {
    boolean success;
    private  String message;
    private GaragePayload payload;

    public GaragePayload getGaragePayload(){
        return this.payload;
    }
}
