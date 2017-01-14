package com.caknow.customer.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Vehicle Service Request Response Payload when searched by vehicleId
 * Created by junu on 1/10/2017.
 */

public class VehicleServicePayload implements Parcelable{

    @SerializedName("vehicleLogo")
    @Expose
    private String vehicleLogo;
    @SerializedName("vehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("vehicleMake")
    @Expose
    private String vehicleMake;
    @SerializedName("vehicleSummary")
    @Expose
    private String vehicleSummary;
    @SerializedName("repairList")
    @Expose
    private List<Repair> repairList = null;
    @SerializedName("maintenanceList")
    @Expose
    private List<Maintenance> maintenanceList = null;

    public String getVehicleLogo() {
        return vehicleLogo;
    }


    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public String getVehicleSummary() {
        return vehicleSummary;
    }

    public List<Repair> getRepairList() {
        return repairList;
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vehicleLogo);
        dest.writeString(this.vehicleId);
        dest.writeString(this.vehicleMake);
        dest.writeString(this.vehicleSummary);
        dest.writeTypedList(this.repairList);
        dest.writeTypedList(this.maintenanceList);
    }

    protected VehicleServicePayload(Parcel in) {
        this.vehicleLogo = in.readString();
        this.vehicleId = in.readString();
        this.vehicleMake = in.readString();
        this.vehicleSummary = in.readString();
        this.repairList = in.createTypedArrayList(Repair.CREATOR);
        this.maintenanceList = in.createTypedArrayList(Maintenance.CREATOR);
    }

    public static final Creator<VehicleServicePayload> CREATOR = new Creator<VehicleServicePayload>() {
        @Override
        public VehicleServicePayload createFromParcel(Parcel source) {
            return new VehicleServicePayload(source);
        }

        @Override
        public VehicleServicePayload[] newArray(int size) {
            return new VehicleServicePayload[size];
        }
    };
}