package com.caknow.customer.util.net.garage.addvehicle;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.garage.GaragePayload;

/**
 * Created by junu on 12/31/16.
 */

public class AddVehicleResponse implements Parcelable{
    boolean success;
    private  String message;
    private GaragePayload payload;

    public GaragePayload getGaragePayload(){
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

    public AddVehicleResponse() {
    }

    protected AddVehicleResponse(Parcel in) {
        this.success = in.readByte() != 0;
        this.message = in.readString();
        this.payload = in.readParcelable(GaragePayload.class.getClassLoader());
    }

    public static final Creator<AddVehicleResponse> CREATOR = new Creator<AddVehicleResponse>() {
        @Override
        public AddVehicleResponse createFromParcel(Parcel source) {
            return new AddVehicleResponse(source);
        }

        @Override
        public AddVehicleResponse[] newArray(int size) {
            return new AddVehicleResponse[size];
        }
    };
}
