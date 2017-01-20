package com.caknow.customer.util.net.quote;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.service.quotes.PriceDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Quote implements Serializable, Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isReQuote")
    @Expose
    private Boolean isReQuote;
    @SerializedName("acceptTime")
    @Expose
    private Long acceptTime;
    @SerializedName("itemizedAmounts")
    @Expose
    private List<PriceDetail> itemizedAmounts = null;
    @SerializedName("status")
    @Expose
    private String status;
    private final static long serialVersionUID = -6955646239782515402L;

    public String getId() {
        return id;
    }

    public Boolean getIsReQuote() {
        return isReQuote;
    }

    public Long getAcceptTime() {
        return acceptTime;
    }

    public List<PriceDetail> getItemizedAmounts() {
        return itemizedAmounts;
    }

    public String getStatus() {
        return status;
    }

    public Quote() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.isReQuote);
        dest.writeValue(this.acceptTime);
        dest.writeTypedList(this.itemizedAmounts);
        dest.writeString(this.status);
    }

    protected Quote(Parcel in) {
        this.id = in.readString();
        this.isReQuote = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.acceptTime = (Long) in.readValue(Long.class.getClassLoader());
        this.itemizedAmounts = in.createTypedArrayList(PriceDetail.CREATOR);
        this.status = in.readString();
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel source) {
            return new Quote(source);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };
}