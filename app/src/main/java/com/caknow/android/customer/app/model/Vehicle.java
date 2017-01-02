package com.caknow.android.customer.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by junu on 1/2/2017.
 */

public class Vehicle implements Parcelable {
    private final String _id;
    private final String name;
    private final String imageUrl;

    public Vehicle(String id, String name, String imageUrl){
        this._id = id;
        this.name = name;
        this.imageUrl = imageUrl;
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
    public String getName(){
        return this.name;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
    }

    protected Vehicle(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
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
