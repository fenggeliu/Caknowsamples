package com.caknow.customer.util.net.history;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class History implements Serializable, Parcelable {

    @SerializedName("serviceRequestId")
    @Expose
    private String serviceRequestId;
    @SerializedName("vehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("affiliateId")
    @Expose
    private String affiliateId;
    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("ut")
    @Expose
    private Long ut;
    @SerializedName("ct")
    @Expose
    private Long ct;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("averageRating")
    @Expose
    private String averageRating;
    @SerializedName("reviewId")
    @Expose
    private String reviewId;
    @SerializedName("shopImage")
    @Expose
    private String shopImage;
    @SerializedName("serviceCategory")
    @Expose
    private String serviceCategory;
    @SerializedName("shopPhone")
    @Expose
    private String shopPhone;
    @SerializedName("shopAddress")
    @Expose
    private String shopAddress;
    @SerializedName("updateTime")
    @Expose
    private Long updateTime;
    @SerializedName("orderNo")
    @Expose
    private String orderNo;
    @SerializedName("serviceFee")
    @Expose
    private String serviceFee;
    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;
    @SerializedName("serviceList")
    @Expose
    private ArrayList<ServiceList> serviceList;
    private final static long serialVersionUID = -6223191523323149301L;

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getAffiliateId() {
        return affiliateId;
    }

    public Long getStatus() {
        return status;
    }

    public Long getUt() {
        return ut;
    }

    public void setUt(Long ut) {
        this.ut = ut;
    }

    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }

    public String getShopName() {
        return shopName;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public java.util.List<ServiceList> getServiceList() {
        return serviceList;
    }

    public History() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.serviceRequestId);
        dest.writeString(this.vehicleId);
        dest.writeString(this.affiliateId);
        dest.writeValue(this.status);
        dest.writeValue(this.ut);
        dest.writeValue(this.ct);
        dest.writeString(this.shopName);
        dest.writeString(this.averageRating);
        dest.writeString(this.reviewId);
        dest.writeString(this.shopImage);
        dest.writeString(this.serviceCategory);
        dest.writeString(this.shopPhone);
        dest.writeString(this.shopAddress);
        dest.writeValue(this.updateTime);
        dest.writeString(this.orderNo);
        dest.writeString(this.serviceFee);
        dest.writeParcelable(this.vehicle, flags);
        dest.writeTypedList(this.serviceList);
    }

    protected History(Parcel in) {
        this.serviceRequestId = in.readString();
        this.vehicleId = in.readString();
        this.affiliateId = in.readString();
        this.status = (Long) in.readValue(Long.class.getClassLoader());
        this.ut = (Long) in.readValue(Long.class.getClassLoader());
        this.ct = (Long) in.readValue(Long.class.getClassLoader());
        this.shopName = in.readString();
        this.averageRating = in.readString();
        this.reviewId = in.readString();
        this.shopImage = in.readString();
        this.serviceCategory = in.readString();
        this.shopPhone = in.readString();
        this.shopAddress = in.readString();
        this.updateTime = (Long) in.readValue(Long.class.getClassLoader());
        this.orderNo = in.readString();
        this.serviceFee = in.readString();
        this.vehicle = in.readParcelable(Vehicle.class.getClassLoader());
        this.serviceList = in.createTypedArrayList(ServiceList.CREATOR);
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel source) {
            return new History(source);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}