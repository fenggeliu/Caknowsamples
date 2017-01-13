package com.caknow.customer.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by junu on 1/10/2017.
 */

public class VehicleServicePayload {

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

    public void setVehicleLogo(String vehicleLogo) {
        this.vehicleLogo = vehicleLogo;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleSummary() {
        return vehicleSummary;
    }

    public void setVehicleSummary(String vehicleSummary) {
        this.vehicleSummary = vehicleSummary;
    }

    public List<Repair> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<Repair> repairList) {
        this.repairList = repairList;
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(List<Maintenance> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

}