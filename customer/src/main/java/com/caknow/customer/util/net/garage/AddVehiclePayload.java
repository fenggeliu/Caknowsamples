package com.caknow.customer.util.net.garage;

import com.caknow.customer.garage.Vehicle;

import java.util.List;

/**
 * Created by junu on 12/31/16.
 */

public class AddVehiclePayload {
    List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }
}
