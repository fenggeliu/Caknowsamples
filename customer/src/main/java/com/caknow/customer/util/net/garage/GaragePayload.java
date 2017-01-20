package com.caknow.customer.util.net.garage;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GaragePayload implements Serializable, Parcelable
{

    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles = null;
    public final static Parcelable.Creator<GaragePayload> CREATOR = new Creator<GaragePayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GaragePayload createFromParcel(Parcel in) {
            GaragePayload instance = new GaragePayload();
            in.readList(instance.vehicles, (com.caknow.customer.util.net.garage.Vehicle.class.getClassLoader()));
            return instance;
        }

        public GaragePayload[] newArray(int size) {
            return (new GaragePayload[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3066162011728687602L;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(vehicles);
    }

    public int describeContents() {
        return 0;
    }

}