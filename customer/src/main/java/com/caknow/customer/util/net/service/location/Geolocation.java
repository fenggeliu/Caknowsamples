package com.caknow.customer.util.net.service.location;

/**
 * Created by jkang on 1/12/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geolocation implements Serializable, Parcelable
{

    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    public final static Parcelable.Creator<Geolocation> CREATOR = new Creator<Geolocation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Geolocation createFromParcel(Parcel in) {
            Geolocation instance = new Geolocation();
            instance.longitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.latitude = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public Geolocation[] newArray(int size) {
            return (new Geolocation[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4757083563858546654L;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.longitude);
        dest.writeValue(this.latitude);
    }

    public int describeContents() {
        return 0;
    }

}