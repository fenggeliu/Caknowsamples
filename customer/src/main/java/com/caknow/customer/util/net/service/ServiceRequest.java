package com.caknow.customer.util.net.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jkang on 1/12/17.
 */

public class ServiceRequest implements Serializable, Parcelable {

    public final static Parcelable.Creator<ServiceRequest> CREATOR = new Creator<ServiceRequest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceRequest createFromParcel(Parcel in) {
            ServiceRequest instance = new ServiceRequest();
            instance.geolocation = ((Geolocation) in.readValue((Geolocation.class.getClassLoader())));
            in.readList(instance.serviceList, (java.lang.String.class.getClassLoader()));
            instance.vehicleId = ((String) in.readValue((String.class.getClassLoader())));
            instance.mileage = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.type = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.priority = ((Long) in.readValue((Long.class.getClassLoader())));
            in.readList(instance.imageList, (java.lang.String.class.getClassLoader()));
            instance.diagnosticCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((ServiceAddress) in.readValue((ServiceAddress.class.getClassLoader())));
            return instance;
        }

        public ServiceRequest[] newArray(int size) {
            return (new ServiceRequest[size]);
        }

    };
    private final static long serialVersionUID = 6945084301464069321L;
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

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getDiagnosticCode() {
        return diagnosticCode;
    }

    public void setDiagnosticCode(String diagnosticCode) {
        this.diagnosticCode = diagnosticCode;
    }

    public ServiceAddress getAddress() {
        return address;
    }

    public void setAddress(ServiceAddress address) {
        this.address = address;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geolocation);
        dest.writeList(serviceList);
        dest.writeValue(vehicleId);
        dest.writeValue(mileage);
        dest.writeValue(type);
        dest.writeValue(description);
        dest.writeValue(priority);
        dest.writeList(imageList);
        dest.writeValue(diagnosticCode);
        dest.writeValue(address);
    }

    public int describeContents() {
        return 0;
    }

}