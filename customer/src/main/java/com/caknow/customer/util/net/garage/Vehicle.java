package com.caknow.customer.util.net.garage;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vehicle implements Serializable, Parcelable {

    @SerializedName("ut")
    @Expose
    private Long ut;
    @SerializedName("ct")
    @Expose
    private Long ct;
    @SerializedName("trim")
    @Expose
    private String trim;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("year")
    @Expose
    private Long year;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("mileage")
    @Expose
    private Long mileage;
    @SerializedName("quoteCount")
    @Expose
    private Long quoteCount;
    private final static long serialVersionUID = 916173834653574585L;

    public Long getUt() {
        return ut;
    }

    public Long getCt() {
        return ct;
    }

    public String getTrim() {
        return trim;
    }

    public String getModel() {
        return model;
    }

    public Long getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getLogo() {
        return logo;
    }

    public String getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public Long getMileage() {
        return mileage;
    }

    public Long getQuoteCount() {
        return quoteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.ut);
        dest.writeValue(this.ct);
        dest.writeString(this.trim);
        dest.writeString(this.model);
        dest.writeValue(this.year);
        dest.writeString(this.make);
        dest.writeString(this.logo);
        dest.writeString(this.id);
        dest.writeValue(this.active);
        dest.writeValue(this.mileage);
        dest.writeValue(this.quoteCount);
    }

    public Vehicle() {
    }

    protected Vehicle(Parcel in) {
        this.ut = (Long) in.readValue(Long.class.getClassLoader());
        this.ct = (Long) in.readValue(Long.class.getClassLoader());
        this.trim = in.readString();
        this.model = in.readString();
        this.year = (Long) in.readValue(Long.class.getClassLoader());
        this.make = in.readString();
        this.logo = in.readString();
        this.id = in.readString();
        this.active = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mileage = (Long) in.readValue(Long.class.getClassLoader());
        this.quoteCount = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel source) {
            return new Vehicle(source);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };
}