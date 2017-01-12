package com.caknow.customer.util.net.service;

/**
 * Created by jkang on 1/12/17.
 */
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geolocation implements Serializable, Parcelable
{

    @SerializedName("longitude")
    @Expose
    private Long longitude;
    @SerializedName("latitude")
    @Expose
    private Long latitude;
    public final static Parcelable.Creator<Geolocation> CREATOR = new Creator<Geolocation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Geolocation createFromParcel(Parcel in) {
            Geolocation instance = new Geolocation();
            instance.longitude = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.latitude = ((Long) in.readValue((Long.class.getClassLoader())));
            return instance;
        }

        public Geolocation[] newArray(int size) {
            return (new Geolocation[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4757083563858546654L;

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
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