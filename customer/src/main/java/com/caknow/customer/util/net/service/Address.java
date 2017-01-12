package com.caknow.customer.util.net.service;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Serializable, Parcelable
{

    @SerializedName("lineOne")
    @Expose
    private String lineOne;
    @SerializedName("lineTwo")
    @Expose
    private String lineTwo;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    public final static Parcelable.Creator<Address> CREATOR = new Creator<Address>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Address createFromParcel(Parcel in) {
            Address instance = new Address();
            instance.lineOne = ((String) in.readValue((String.class.getClassLoader())));
            instance.lineTwo = ((String) in.readValue((String.class.getClassLoader())));
            instance.city = ((String) in.readValue((String.class.getClassLoader())));
            instance.state = ((String) in.readValue((String.class.getClassLoader())));
            instance.postalCode = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    }
            ;
    private final static long serialVersionUID = -23467204884983325L;

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lineOne);
        dest.writeValue(lineTwo);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(postalCode);
    }

    public int describeContents() {
        return 0;
    }

}