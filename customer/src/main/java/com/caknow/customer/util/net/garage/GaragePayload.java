package com.caknow.customer.util.net.garage;

import com.caknow.customer.garage.Vehicle;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by junu on 12/31/16.
 */

public class GaragePayload {
    List<Vehicle> vehicles;

    public List<Vehicle> getVehicles(){
        return this.vehicles;
    }
}
