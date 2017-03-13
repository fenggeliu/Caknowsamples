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
    @SerializedName("chargeAcceptNewestDifferenceAmountNumber")
    @Expose
    private long chargeAcceptNewestDifferenceAmountNumber;
    @SerializedName("currentAndInitialNetDifferenceNumber")
    @Expose
    private long currentAndInitialNetDifferenceNumber;
    @SerializedName("quotes")
    @Expose
    private List<Quote> quotes;
    @SerializedName("topQuote")
    @Expose
    private Quote topQuote;
    @SerializedName("ct")
    @Expose
    private long ct;
    @SerializedName("remark")
    @Expose
    private String remark;
    private final static long serialVersionUID = 8385809350563275408L;

    public String getChargeOriginalAmount() {
        return chargeOriginalAmount;
    }



    public String getChargeAcceptNewestDifferenceAmount() {
        return chargeAcceptNewestDifferenceAmount;
    }

    public long getChargeAcceptNewestDifferenceAmountNumber() {
        return chargeAcceptNewestDifferenceAmountNumber;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public Quote getTopQuote() {
        return topQuote;
    }

    public long getCt() {
        return ct;
    }

    public String getRemark() {
        return remark;
    }

    public GetQuotesByServiceIdPayload() {
    }

    public long getCurrentAndInitialNetDifferenceNumber() {
        return currentAndInitialNetDifferenceNumber;
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
        dest.writeValue(this.topQuote);
        dest.writeValue(this.ct);
        dest.writeString(this.remark);
        dest.writeValue(this.chargeAcceptNewestDifferenceAmountNumber);
        dest.writeValue(this.currentAndInitialNetDifferenceNumber);
    }

    protected GetQuotesByServiceIdPayload(Parcel in) {
        this.chargeOriginalAmount = in.readString();
        this.chargeAcceptNewestDifferenceAmount = in.readString();
        this.quotes = in.createTypedArrayList(Quote.CREATOR);
        this.topQuote = ((Quote) in.readValue(Quote.class.getClassLoader()));
        this.ct = ((long)in.readValue(Quote.class.getClassLoader()));
        this.remark = in.readString();
        this.chargeAcceptNewestDifferenceAmountNumber = ((long)in.readValue(Quote.class.getClassLoader()));
        this.currentAndInitialNetDifferenceNumber = ((long)in.readValue(Quote.class.getClassLoader()));
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