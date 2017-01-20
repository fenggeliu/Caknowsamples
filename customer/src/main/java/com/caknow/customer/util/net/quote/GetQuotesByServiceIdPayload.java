package com.caknow.customer.util.net.quote;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetQuotesByServiceIdPayload implements Serializable, Parcelable {

    @SerializedName("chargeOriginalAmount")
    @Expose
    private String chargeOriginalAmount;
    @SerializedName("chargeAcceptNewestDifferenceAmount")
    @Expose
    private String chargeAcceptNewestDifferenceAmount;
    @SerializedName("quotes")
    @Expose
    private List<Quote> quotes = null;
    private final static long serialVersionUID = 8385809350563275408L;

    public String getChargeOriginalAmount() {
        return chargeOriginalAmount;
    }



    public String getChargeAcceptNewestDifferenceAmount() {
        return chargeAcceptNewestDifferenceAmount;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.chargeOriginalAmount);
        dest.writeString(this.chargeAcceptNewestDifferenceAmount);
        dest.writeTypedList(this.quotes);
    }

    public GetQuotesByServiceIdPayload() {
    }

    protected GetQuotesByServiceIdPayload(Parcel in) {
        this.chargeOriginalAmount = in.readString();
        this.chargeAcceptNewestDifferenceAmount = in.readString();
        this.quotes = in.createTypedArrayList(Quote.CREATOR);
    }

    public static final Creator<GetQuotesByServiceIdPayload> CREATOR = new Creator<GetQuotesByServiceIdPayload>() {
        @Override
        public GetQuotesByServiceIdPayload createFromParcel(Parcel source) {
            return new GetQuotesByServiceIdPayload(source);
        }

        @Override
        public GetQuotesByServiceIdPayload[] newArray(int size) {
            return new GetQuotesByServiceIdPayload[size];
        }
    };
}