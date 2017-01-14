package com.caknow.customer.service.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by junu on 1/10/2017.
 */

public class Maintenance implements VehicleServiceInterface{


    @SerializedName("serviceRequestId")
    @Expose
    private String serviceRequestId;
    @SerializedName("createTime")
    @Expose
    private Long createTime;
    @SerializedName("orderNo")
    @Expose
    private String orderNo;
    @SerializedName("serviceCatagory")
    @Expose
    private String serviceCatagory;
    @SerializedName("serviceField")
    @Expose
    private String serviceField;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("quoteCount")
    @Expose
    private Integer quoteCount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("type")
    @Expose
    private Integer type;

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    @Override
    public Long getCreateTime() {
        return createTime;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getServiceCatagory() {
        return serviceCatagory;
    }

    public void setServiceCatagory(String serviceCatagory) {
        this.serviceCatagory = serviceCatagory;
    }

    public String getServiceField() {
        return serviceField;
    }

    public void setServiceField(String serviceField) {
        this.serviceField = serviceField;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(Integer quoteCount) {
        this.quoteCount = quoteCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String getDisplayTitle() {
        return this.serviceCatagory;
    }


    @Override
    public Long getDate() {
        return this.createTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.serviceRequestId);
        dest.writeValue(this.createTime);
        dest.writeString(this.orderNo);
        dest.writeString(this.serviceCatagory);
        dest.writeString(this.serviceField);
        dest.writeString(this.iconUrl);
        dest.writeValue(this.quoteCount);
        dest.writeValue(this.status);
        dest.writeValue(this.type);
    }

    public Maintenance() {
    }

    protected Maintenance(Parcel in) {
        this.serviceRequestId = in.readString();
        this.createTime = (Long) in.readValue(Long.class.getClassLoader());
        this.orderNo = in.readString();
        this.serviceCatagory = in.readString();
        this.serviceField = in.readString();
        this.iconUrl = in.readString();
        this.quoteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Maintenance> CREATOR = new Creator<Maintenance>() {
        @Override
        public Maintenance createFromParcel(Parcel source) {
            return new Maintenance(source);
        }

        @Override
        public Maintenance[] newArray(int size) {
            return new Maintenance[size];
        }
    };
}

