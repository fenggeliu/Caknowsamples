package com.caknow.customer.util.net;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.garage.addvehicle.AddVehicleRequest;
import com.caknow.customer.util.net.payment.AddPaymentRequest;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by jkang on 1/20/17.
 */

public class BaseRequestBody {

    public static String getJsonString(BaseRequestBody request){
        Gson gson = new Gson();

        return gson.toJson(request);
    }

    public static RequestBody getRequestBody(BaseRequestBody request){
        return RequestBody.create(MediaType.parse("application/json"), getJsonString(request));
    }


}