package com.caknow.customer.util.net.service.quotes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemizedAmount implements Serializable, Parcelable {

    @SerializedName("priceItem")
    @Expose
    private String priceItem;
    @SerializedName("price")
    @Expose
    private String price;
    public final static Parcelable.Creator<ItemizedAmount> CREATOR = new Creator<ItemizedAmount>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ItemizedAmount createFromParcel(Parcel in) {
            ItemizedAmount instance = new ItemizedAmount();
            instance.priceItem = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ItemizedAmount[] newArray(int size) {
            return (new ItemizedAmount[size]);
        }

    };
    private final static long serialVersionUID = 3912090367168365706L;

    public String getPriceItem() {
        return priceItem;
    }


    public String getPrice() {
        return price;
    }



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(priceItem);
        dest.writeValue(price);
    }

    public int describeContents() {
        return 0;
    }

}