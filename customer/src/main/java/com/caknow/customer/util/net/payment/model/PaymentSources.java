package com.caknow.customer.util.net.payment.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentSources implements Serializable, Parcelable
{

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("data")
    @Expose
    private List<PaymentMethodItem> data = null;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;
    @SerializedName("total_count")
    @Expose
    private Long totalCount;
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = 7921001933746809157L;

    public String getObject() {
        return object;
    }

    public List<PaymentMethodItem> getData() {
        return data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.object);
        dest.writeTypedList(this.data);
        dest.writeValue(this.hasMore);
        dest.writeValue(this.totalCount);
        dest.writeString(this.url);
    }

    public PaymentSources() {
    }

    protected PaymentSources(Parcel in) {
        this.object = in.readString();
        this.data = in.createTypedArrayList(PaymentMethodItem.CREATOR);
        this.hasMore = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.totalCount = (Long) in.readValue(Long.class.getClassLoader());
        this.url = in.readString();
    }

    public static final Creator<PaymentSources> CREATOR = new Creator<PaymentSources>() {
        @Override
        public PaymentSources createFromParcel(Parcel source) {
            return new PaymentSources(source);
        }

        @Override
        public PaymentSources[] newArray(int size) {
            return new PaymentSources[size];
        }
    };
}