package com.caknow.customer.util.net.history;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle implements Serializable, Parcelable
{

    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("trim")
    @Expose
    private String trim;
    @SerializedName("mileage")
    @Expose
    private String mileage;
    @SerializedName("vehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("consumerName")
    @Expose
    private String consumerName;
    @SerializedName("consumerAvatar")
    @Expose
    private String consumerAvatar;
    public final static Parcelable.Creator<Vehicle> CREATOR = new Creator<Vehicle>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Vehicle createFromParcel(Parcel in) {
            Vehicle instance = new Vehicle();
            instance.year = ((String) in.readValue((String.class.getClassLoader())));
            instance.make = ((String) in.readValue((String.class.getClassLoader())));
            instance.model = ((String) in.readValue((String.class.getClassLoader())));
            instance.trim = ((String) in.readValue((String.class.getClassLoader())));
            instance.mileage = ((String) in.readValue((String.class.getClassLoader())));
            instance.vehicleId = ((String) in.readValue((String.class.getClassLoader())));
            instance.consumerName = ((String) in.readValue((String.class.getClassLoader())));
            instance.consumerAvatar = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Vehicle[] newArray(int size) {
            return (new Vehicle[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1484133850256199820L;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerAvatar() {
        return consumerAvatar;
    }

    public void setConsumerAvatar(String consumerAvatar) {
        this.consumerAvatar = consumerAvatar;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(year);
        dest.writeValue(make);
        dest.writeValue(model);
        dest.writeValue(trim);
        dest.writeValue(mileage);
        dest.writeValue(vehicleId);
        dest.writeValue(consumerName);
        dest.writeValue(consumerAvatar);
    }

    public int describeContents() {
        return 0;
    }

}