package com.caknow.customer.util.net.service;

/**
 * Created by junu on 1/13/2017.
 */

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.caknow.customer.util.net.content.LoginRequestPayload;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceRequestPayload implements Serializable, Parcelable
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
    private int priority;
    @SerializedName("imageList")
    @Expose
    private List<String> imageList = null;
    @SerializedName("diagnosticCode")
    @Expose
    private String diagnosticCode;
    @SerializedName("address")
    @Expose
    private ServiceAddress address;
    public final static Parcelable.Creator<ServiceRequestPayload> CREATOR = new Creator<ServiceRequestPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceRequestPayload createFromParcel(Parcel in) {
            ServiceRequestPayload instance = new ServiceRequestPayload();
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

        public ServiceRequestPayload[] newArray(int size) {
            return (new ServiceRequestPayload[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1837018652848126229L;

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

    public void setPriority(int priority) {
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
    public static String getJsonString(ServiceRequestPayload payload){
        Gson gson = new Gson();

        return gson.toJson(payload);
    }

}