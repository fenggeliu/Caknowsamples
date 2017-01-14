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
    public final static Parcelable.Creator<GetQuotesByServiceIdPayload> CREATOR = new Creator<GetQuotesByServiceIdPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetQuotesByServiceIdPayload createFromParcel(Parcel in) {
            GetQuotesByServiceIdPayload instance = new GetQuotesByServiceIdPayload();
            instance.chargeOriginalAmount = ((String) in.readValue((String.class.getClassLoader())));
            instance.chargeAcceptNewestDifferenceAmount = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.quotes, (com.caknow.customer.util.net.quote.Quote.class.getClassLoader()));
            return instance;
        }

        public GetQuotesByServiceIdPayload[] newArray(int size) {
            return (new GetQuotesByServiceIdPayload[size]);
        }

    };
    private final static long serialVersionUID = 8385809350563275408L;

    public String getChargeOriginalAmount() {
        return chargeOriginalAmount;
    }

    public void setChargeOriginalAmount(String chargeOriginalAmount) {
        this.chargeOriginalAmount = chargeOriginalAmount;
    }

    public GetQuotesByServiceIdPayload withChargeOriginalAmount(String chargeOriginalAmount) {
        this.chargeOriginalAmount = chargeOriginalAmount;
        return this;
    }

    public String getChargeAcceptNewestDifferenceAmount() {
        return chargeAcceptNewestDifferenceAmount;
    }

    public void setChargeAcceptNewestDifferenceAmount(String chargeAcceptNewestDifferenceAmount) {
        this.chargeAcceptNewestDifferenceAmount = chargeAcceptNewestDifferenceAmount;
    }

    public GetQuotesByServiceIdPayload withChargeAcceptNewestDifferenceAmount(String chargeAcceptNewestDifferenceAmount) {
        this.chargeAcceptNewestDifferenceAmount = chargeAcceptNewestDifferenceAmount;
        return this;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public GetQuotesByServiceIdPayload withQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(chargeOriginalAmount);
        dest.writeValue(chargeAcceptNewestDifferenceAmount);
        dest.writeList(quotes);
    }

    public int describeContents() {
        return 0;
    }

}