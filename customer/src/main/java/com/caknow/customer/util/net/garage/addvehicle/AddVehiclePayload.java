package com.caknow.customer.util.net.garage.addvehicle;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.garage.Vehicle;

import java.util.List;

/**
 * Created by junu on 12/31/16.
 */

public class AddVehiclePayload implements Parcelable
{
    List<Vehicle> vehicles;

    public List<Vehicle> getVehicles(){
        return this.vehicles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.vehicles);
    }

    public AddVehiclePayload() {
    }

    protected AddVehiclePayload(Parcel in) {
        this.vehicles = in.createTypedArrayList(Vehicle.CREATOR);
    }

    public static final Creator<AddVehiclePayload> CREATOR = new Creator<AddVehiclePayload>() {
        @Override
        public AddVehiclePayload createFromParcel(Parcel source) {
            return new AddVehiclePayload(source);
        }

        @Override
        public AddVehiclePayload[] newArray(int size) {
            return new AddVehiclePayload[size];
        }
    };
}
