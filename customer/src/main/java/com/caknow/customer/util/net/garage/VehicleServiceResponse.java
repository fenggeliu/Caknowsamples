package com.caknow.customer.util.net.garage;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.service.model.VehicleServicePayload;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by junu on 1/10/2017.
 */

public class VehicleServiceResponse implements Serializable, Parcelable {
    boolean success;
    String message;
    @SerializedName("payload")VehicleServicePayload payload;


    public VehicleServicePayload getServiceRequests(){
        return this.payload;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
        dest.writeParcelable(this.payload, flags);
    }

    public VehicleServiceResponse() {
    }

    protected VehicleServiceResponse(Parcel in) {
        this.success = in.readByte() != 0;
        this.message = in.readString();
        this.payload = in.readParcelable(VehicleServicePayload.class.getClassLoader());
    }

    public static final Creator<VehicleServiceResponse> CREATOR = new Creator<VehicleServiceResponse>() {
        @Override
        public VehicleServiceResponse createFromParcel(Parcel source) {
            return new VehicleServiceResponse(source);
        }

        @Override
        public VehicleServiceResponse[] newArray(int size) {
            return new VehicleServiceResponse[size];
        }
    };
}
