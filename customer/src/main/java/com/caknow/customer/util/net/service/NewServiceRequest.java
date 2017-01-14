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
    public final static Parcelable.Creator<NewServiceRequest> CREATOR = new Creator<NewServiceRequest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NewServiceRequest createFromParcel(Parcel in) {
            NewServiceRequest instance = new NewServiceRequest();
            instance.geolocation = ((Geolocation) in.readValue((Geolocation.class.getClassLoader())));
            in.readList(instance.serviceList, (java.lang.String.class.getClassLoader()));
            instance.vehicleId = ((String) in.readValue((String.class.getClassLoader())));
            instance.mileage = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.type = ((int) in.readValue((Long.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.priority = ((int) in.readValue((Long.class.getClassLoader())));
            in.readList(instance.imageList, (java.lang.String.class.getClassLoader()));
            instance.diagnosticCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((ServiceAddress) in.readValue((ServiceAddress.class.getClassLoader())));
            return instance;
        }

        public NewServiceRequest[] newArray(int size) {
            return (new NewServiceRequest[size]);
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
    public static String getJsonString(NewServiceRequest payload){
        Gson gson = new Gson();

        return gson.toJson(payload);
    }

}