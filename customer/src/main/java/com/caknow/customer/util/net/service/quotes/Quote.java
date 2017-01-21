package com.caknow.customer.util.net.service.quotes;

import android.os.Parcel;
import android.os.Parcelable;

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
    private List<ItemizedAmount> itemizedAmounts;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<Quote> CREATOR = new Creator<Quote>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Quote createFromParcel(Parcel in) {
            Quote instance = new Quote();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.isReQuote = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.acceptTime = ((Long) in.readValue((Long.class.getClassLoader())));
            in.readList(instance.itemizedAmounts, (com.caknow.customer.util.net.service.quotes.ItemizedAmount.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Quote[] newArray(int size) {
            return (new Quote[size]);
        }

    };

    private final static long serialVersionUID = -6722088711181714501L;

    public String getId() {
        return id;
    }

    public Boolean getIsReQuote() {
        return isReQuote;
    }

    public Long getAcceptTime() {
        return acceptTime;
    }

    public List<ItemizedAmount> getItemizedAmounts() {
        return itemizedAmounts;
    }

    public String getStatus() {
        return status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(isReQuote);
        dest.writeValue(acceptTime);
        dest.writeList(itemizedAmounts);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
