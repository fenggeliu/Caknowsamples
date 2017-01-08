package com.caknow.customer.garage;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.service.model.ServiceItem;
import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by junu on 1/2/2017.
 */

public class Vehicle implements Parcelable {
    public static final String PARCELABLE_KEY = Vehicle.class.getName();
    private long ut;
    private long ct;

    public String getTrim() {
        return trim;
    }

    public String getModel() {
        return model;
    }

    public long getCt() {
        return ct;
    }

    public long getUt() {
        return ut;
    }

    public long getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public boolean isActive() {
        return active;
    }

    public long getMileage() {
        return mileage;
    }

    public int getQuoteCount() {
        return quoteCount;
    }

    public List<ServiceItem> getServiceItemList() {
        return serviceItemList;
    }

    private String trim;
    private String model;
    private long year;
    private String make;
    @SerializedName("logo") private String imageUrl;
    private String _id;
    private boolean active;
    private long mileage;
    private int quoteCount;
    private List<ServiceItem> serviceItemList;


    public Vehicle(String id, String name, String imageUrl){
        this._id = id;
        this.imageUrl = imageUrl;

    }

    public Vehicle(String year, String make, String model, String trim){
        try {
            this.year = Long.valueOf(year);
        } catch(NumberFormatException e){
            //
        }
        this.make = make;
        this.model = model;
        this.trim = trim;
    }

    /**
     * Returns the unique id for vehicle
     * @return
     */
    public String getId(){
        return this._id;
    }

    /**
     * Returns the display name for the vehicle
     * @return
     */

    public String getImageUrl(){
        return this.imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.ut);
        dest.writeLong(this.ct);
        dest.writeString(this.trim);
        dest.writeString(this.model);
        dest.writeLong(this.year);
        dest.writeString(this.make);
        dest.writeString(this.imageUrl);
        dest.writeString(this._id);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeLong(this.mileage);
        dest.writeInt(this.quoteCount);
        dest.writeList(this.serviceItemList);
    }

    protected Vehicle(Parcel in) {
        this.ut = in.readLong();
        this.ct = in.readLong();
        this.trim = in.readString();
        this.model = in.readString();
        this.year = in.readLong();
        this.make = in.readString();
        this.imageUrl = in.readString();
        this._id = in.readString();
        this.active = in.readByte() != 0;
        this.mileage = in.readLong();
        this.quoteCount = in.readInt();
        this.serviceItemList = new ArrayList<ServiceItem>();
        in.readList(this.serviceItemList, ServiceItem.class.getClassLoader());
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
