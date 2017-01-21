package com.caknow.customer.util.net.service;

/**
 * Created by junu on 1/13/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.service.location.Geolocation;
import com.caknow.customer.util.net.service.location.ServiceAddress;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NewServiceRequest implements Serializable, Parcelable
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
    private int type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
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

    public static String getJsonString(NewServiceRequest payload){
        Gson gson = new Gson();

        return gson.toJson(payload);
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
        dest.writeInt(this.type);
        dest.writeString(this.description);
        dest.writeInt(this.priority);
        dest.writeStringList(this.imageList);
        dest.writeString(this.diagnosticCode);
        dest.writeParcelable(this.address, flags);
    }

    public NewServiceRequest() {
    }

    protected NewServiceRequest(Parcel in) {
        this.geolocation = in.readParcelable(Geolocation.class.getClassLoader());
        this.serviceList = in.createStringArrayList();
        this.vehicleId = in.readString();
        this.mileage = (Long) in.readValue(Long.class.getClassLoader());
        this.type = in.readInt();
        this.description = in.readString();
        this.priority = in.readInt();
        this.imageList = in.createStringArrayList();
        this.diagnosticCode = in.readString();
        this.address = in.readParcelable(ServiceAddress.class.getClassLoader());
    }

    public static final Creator<NewServiceRequest> CREATOR = new Creator<NewServiceRequest>() {
        @Override
        public NewServiceRequest createFromParcel(Parcel source) {
            return new NewServiceRequest(source);
        }

        @Override
        public NewServiceRequest[] newArray(int size) {
            return new NewServiceRequest[size];
        }
    };
}