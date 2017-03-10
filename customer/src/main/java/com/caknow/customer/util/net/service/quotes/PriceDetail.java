package com.caknow.customer.util.net.service.quotes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PriceDetail implements Serializable, Parcelable {

    public final static Parcelable.Creator<PriceDetail> CREATOR = new Creator<PriceDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PriceDetail createFromParcel(Parcel in) {
            PriceDetail instance = new PriceDetail();
            instance.priceItem = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public PriceDetail[] newArray(int size) {
            return (new PriceDetail[size]);
        }

    };
    private final static long serialVersionUID = 6647947153825320027L;
    @SerializedName("priceItem")
    @Expose
    private String priceItem;
    @SerializedName("price")
    @Expose
    private String price;

    public String getPriceItem() {
        return priceItem;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(priceItem);
        dest.writeValue(price);
    }

    public int describeContents() {
        return 0;
    }

}