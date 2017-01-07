package com.caknow.customer.util.net.garage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 1/6/17.
 */

public class AddVehicleRequest {

    private String year;
    private String make;
    private String model;
    private String trim;
    private String mileage;


    public AddVehicleRequest(String year, String make, String model, String trim, String mileage) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.mileage = mileage;
    }


    public static String getJsonString(AddVehicleRequest request){
        Gson gson = new Gson();

        return gson.toJson(request);
    }
}
