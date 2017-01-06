package com.caknow.customer.service.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jkang on 1/4/17.
 */
public class LocationItem implements Parcelable{

    public static final String PARCELABLE_KEY = LocationItem.class.getName();
    // ===========================================================
    // Constants
    // ===========================================================
    String address1;
    String address2;
    String city;
    String zip;
    String state;

    // ===========================================================
    // Fields
    // ===========================================================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address1);
        dest.writeString(this.address2);
        dest.writeString(this.city);
        dest.writeString(this.zip);
        dest.writeString(this.state);
    }

    public LocationItem(String address1, String address2, String city, String zip) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.zip = zip;
    }

    protected LocationItem(Parcel in) {
        this.address1 = in.readString();
        this.address2 = in.readString();
        this.city = in.readString();
        this.zip = in.readString();
        this.state = in.readString();
    }

    public static final Creator<LocationItem> CREATOR = new Creator<LocationItem>() {
        @Override
        public LocationItem createFromParcel(Parcel source) {
            return new LocationItem(source);
        }

        @Override
        public LocationItem[] newArray(int size) {
            return new LocationItem[size];
        }
    };
}
