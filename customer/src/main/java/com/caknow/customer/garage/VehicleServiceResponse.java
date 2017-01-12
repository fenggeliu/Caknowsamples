package com.caknow.customer.garage;

import com.caknow.customer.service.model.VehicleServicePayload;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by junu on 1/10/2017.
 */

public class VehicleServiceResponse implements Serializable {
    boolean success;
    String message;
    @SerializedName("payload")VehicleServicePayload payload;


    public VehicleServicePayload getServiceRequests(){
        return this.payload;
    }
}
