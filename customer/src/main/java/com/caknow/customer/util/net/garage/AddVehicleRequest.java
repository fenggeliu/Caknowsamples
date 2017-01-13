package com.caknow.customer.util.net.garage;

import com.google.gson.Gson;

/**
 * Created by junu on 1/6/17.
 */

public class AddVehicleRequest {

    private String year;
    private String make;
    private String model;
    private String trim;
    private String mileage;
    private String logo;


    public AddVehicleRequest(String year, String make, String model, String trim, String mileage, String logoUrl) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.mileage = mileage;
        this.logo = logoUrl;
    }


    public static String getJsonString(AddVehicleRequest request) {
        Gson gson = new Gson();

        return gson.toJson(request);
    }
}
