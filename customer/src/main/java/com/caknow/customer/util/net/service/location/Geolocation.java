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
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    public final static Parcelable.Creator<Geolocation> CREATOR = new Creator<Geolocation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Geolocation createFromParcel(Parcel in) {
            Geolocation instance = new Geolocation();
            instance.longitude = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.latitude = ((Double) in.readValue((Double.class.getClassLoader())));
            return instance;
        }

        public Geolocation[] newArray(int size) {
            return (new Geolocation[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4757083563858546654L;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(longitude);
        dest.writeValue(latitude);
    }

    public int describeContents() {
        return 0;
    }

}