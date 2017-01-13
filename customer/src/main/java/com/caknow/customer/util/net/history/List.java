package com.caknow.customer.util.net.history;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class List implements Serializable, Parcelable {

    public final static Parcelable.Creator<List> CREATOR = new Creator<List>() {


        @SuppressWarnings({
                "unchecked"
        })
        public List createFromParcel(Parcel in) {
            List instance = new List();
            instance.serviceRequestId = ((String) in.readValue((String.class.getClassLoader())));
            instance.vehicleId = ((String) in.readValue((String.class.getClassLoader())));
            instance.affiliateId = ((String) in.readValue((String.class.getClassLoader())));
            instance.status = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.ut = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.ct = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.shopName = ((String) in.readValue((String.class.getClassLoader())));
            instance.averageRating = ((String) in.readValue((String.class.getClassLoader())));
            instance.reviewId = ((String) in.readValue((String.class.getClassLoader())));
            instance.shopImage = ((String) in.readValue((String.class.getClassLoader())));
            instance.serviceCategory = ((String) in.readValue((String.class.getClassLoader())));
            instance.shopPhone = ((String) in.readValue((String.class.getClassLoader())));
            instance.shopAddress = ((String) in.readValue((String.class.getClassLoader())));
            instance.updateTime = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.orderNo = ((String) in.readValue((String.class.getClassLoader())));
            instance.serviceFee = ((String) in.readValue((String.class.getClassLoader())));
            instance.vehicle = ((Vehicle) in.readValue((Vehicle.class.getClassLoader())));
            in.readList(instance.serviceList, (com.caknow.customer.util.net.history.ServiceList.class.getClassLoader()));
            return instance;
        }

        public List[] newArray(int size) {
            return (new List[size]);
        }

    };
    private final static long serialVersionUID = -6223191523323149301L;
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
    private java.util.List<ServiceList> serviceList = null;

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(String affiliateId) {
        this.affiliateId = affiliateId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public java.util.List<ServiceList> getServiceList() {
        return serviceList;
    }

    public void setServiceList(java.util.List<ServiceList> serviceList) {
        this.serviceList = serviceList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(serviceRequestId);
        dest.writeValue(vehicleId);
        dest.writeValue(affiliateId);
        dest.writeValue(status);
        dest.writeValue(ut);
        dest.writeValue(ct);
        dest.writeValue(shopName);
        dest.writeValue(averageRating);
        dest.writeValue(reviewId);
        dest.writeValue(shopImage);
        dest.writeValue(serviceCategory);
        dest.writeValue(shopPhone);
        dest.writeValue(shopAddress);
        dest.writeValue(updateTime);
        dest.writeValue(orderNo);
        dest.writeValue(serviceFee);
        dest.writeValue(vehicle);
        dest.writeList(serviceList);
    }

    public int describeContents() {
        return 0;
    }

}