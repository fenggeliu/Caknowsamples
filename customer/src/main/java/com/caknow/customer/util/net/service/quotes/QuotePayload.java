
package com.caknow.customer.util.net.service.quotes;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.quotes.ServiceList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuotePayload implements Serializable, Parcelable
{
    @SerializedName("affiliate")
    @Expose
    private Affiliate affiliate;
    @SerializedName("serviceRequestId")
    @Expose
    private String serviceRequestId;
    @SerializedName("orderNo")
    @Expose
    private String orderNo;
    @SerializedName("vehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("serviceTypeName")
    @Expose
    private String serviceTypeName;
    @SerializedName("serviceType")
    @Expose
    private Long serviceType;
    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("timeframe")
    @Expose
    private String timeframe;
    @SerializedName("priority")
    @Expose
    private Long priority;
    @SerializedName("serviceList")
    @Expose
    private List<ServiceList> serviceList = null;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("quoteList")
    @Expose
    private List<QuoteList> quoteList = null;
    private final static long serialVersionUID = -5387812240366608317L;

    public String getServiceRequestId() {
        return serviceRequestId;
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

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public Affiliate getAffiliate(){
        return this.affiliate;
    }

    public void setAffiliate(Affiliate affiliate){
        this.affiliate = affiliate;
    }

    public Long getServiceType() {
        return serviceType;
    }

    public void setServiceType(Long serviceType) {
        this.serviceType = serviceType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public List<ServiceList> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceList> serviceList) {
        this.serviceList = serviceList;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public List<QuoteList> getQuoteList() {
        return quoteList;
    }

    public void setQuoteList(List<QuoteList> quoteList) {
        this.quoteList = quoteList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.affiliate, flags);
        dest.writeString(this.serviceRequestId);
        dest.writeString(this.orderNo);
        dest.writeString(this.vehicleId);
        dest.writeString(this.serviceTypeName);
        dest.writeValue(this.serviceType);
        dest.writeValue(this.status);
        dest.writeString(this.timeframe);
        dest.writeValue(this.priority);
        dest.writeTypedList(this.serviceList);
        dest.writeValue(this.longitude);
        dest.writeValue(this.latitude);
        dest.writeTypedList(this.quoteList);
    }

    public QuotePayload() {
    }

    protected QuotePayload(Parcel in) {
        this.affiliate = in.readParcelable(Affiliate.class.getClassLoader());
        this.serviceRequestId = in.readString();
        this.orderNo = in.readString();
        this.vehicleId = in.readString();
        this.serviceTypeName = in.readString();
        this.serviceType = (Long) in.readValue(Long.class.getClassLoader());
        this.status = (Long) in.readValue(Long.class.getClassLoader());
        this.timeframe = in.readString();
        this.priority = (Long) in.readValue(Long.class.getClassLoader());
        this.serviceList = in.createTypedArrayList(ServiceList.CREATOR);
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.quoteList = in.createTypedArrayList(QuoteList.CREATOR);
    }

    public static final Creator<QuotePayload> CREATOR = new Creator<QuotePayload>() {
        @Override
        public QuotePayload createFromParcel(Parcel source) {
            return new QuotePayload(source);
        }

        @Override
        public QuotePayload[] newArray(int size) {
            return new QuotePayload[size];
        }
    };
}