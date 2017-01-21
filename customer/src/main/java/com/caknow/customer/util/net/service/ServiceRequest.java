package com.caknow.customer.util.net.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.service.location.Geolocation;
import com.caknow.customer.util.net.service.location.ServiceAddress;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jkang on 1/12/17.
 */

public class ServiceRequest implements Serializable, Parcelable
{

    @SerializedName("geolocation")
    @Expose
    private Geolocation geolocation;
    @SerializedName("serviceList")
    @Expose
    private List<String> serviceList = null;
    @SerializedName("vehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("mileage")
    @Expose
    private Long mileage;
    @SerializedName("type")
    @Expose
    private Long type;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("priority")
    @Expose
    private Long priority;
    @SerializedName("imageList")
    @Expose
    private List<String> imageList = null;
    @SerializedName("diagnosticCode")
    @Expose
    private String diagnosticCode;
    @SerializedName("address")
    @Expose
    private ServiceAddress address;
    private final static long serialVersionUID = 6945084301464069321L;

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public Long getMileage() {
        return mileage;
    }

    public Long getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Long getPriority() {
        return priority;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public String getDiagnosticCode() {
        return diagnosticCode;
    }

    public ServiceAddress getAddress() {
        return address;
    }

    public ServiceRequest() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.geolocation, flags);
        dest.writeStringList(this.serviceList);
        dest.writeString(this.vehicleId);
        dest.writeValue(this.mileage);
        dest.writeValue(this.type);
        dest.writeString(this.description);
        dest.writeValue(this.priority);
        dest.writeStringList(this.imageList);
        dest.writeString(this.diagnosticCode);
        dest.writeParcelable(this.address, flags);
    }

    protected ServiceRequest(Parcel in) {
        this.geolocation = in.readParcelable(Geolocation.class.getClassLoader());
        this.serviceList = in.createStringArrayList();
        this.vehicleId = in.readString();
        this.mileage = (Long) in.readValue(Long.class.getClassLoader());
        this.type = (Long) in.readValue(Long.class.getClassLoader());
        this.description = in.readString();
        this.priority = (Long) in.readValue(Long.class.getClassLoader());
        this.imageList = in.createStringArrayList();
        this.diagnosticCode = in.readString();
        this.address = in.readParcelable(ServiceAddress.class.getClassLoader());
    }

    public static final Creator<ServiceRequest> CREATOR = new Creator<ServiceRequest>() {
        @Override
        public ServiceRequest createFromParcel(Parcel source) {
            return new ServiceRequest(source);
        }

        @Override
        public ServiceRequest[] newArray(int size) {
            return new ServiceRequest[size];
        }
    };
}