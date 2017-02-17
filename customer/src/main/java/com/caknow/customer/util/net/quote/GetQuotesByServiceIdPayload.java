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
    @SerializedName("topQuote")
    @Expose
    private List<Quote> topQuote;
    @SerializedName("quotes")
    @Expose
    private List<Quote> quotes;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("ct")
    @Expose
    private int ct;
    private final static long serialVersionUID = 8385809350563275408L;
    //private final static long serialVersionUID = -6411214159794046989L;
    public String getChargeOriginalAmount() {
        return chargeOriginalAmount;
    }



    public String getChargeAcceptNewestDifferenceAmount() {
        return chargeAcceptNewestDifferenceAmount;
    }

    public List<Quote> getTopQuote() {
        return topQuote;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }


    public GetQuotesByServiceIdPayload() {
    }
    /**
     *
     * @param quotes
     * @param topQuote
     * @param remark
     * @param chargeOriginalAmount
     * @param chargeAcceptNewestDifferenceAmount
     * @param ct
     */
    public GetQuotesByServiceIdPayload(String chargeOriginalAmount, String chargeAcceptNewestDifferenceAmount, List<Quote> topQuote, List<Quote> quotes, String remark, Integer ct) {
        super();
        this.chargeOriginalAmount = chargeOriginalAmount;
        this.chargeAcceptNewestDifferenceAmount = chargeAcceptNewestDifferenceAmount;
        this.topQuote = topQuote;
        this.quotes = quotes;
        this.remark = remark;
        this.ct = ct;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.chargeOriginalAmount);
        dest.writeString(this.chargeAcceptNewestDifferenceAmount);
        dest.writeTypedList(this.topQuote);
        dest.writeTypedList(this.quotes);
        dest.writeValue(this.remark);
        dest.writeValue(this.ct);
    }

//    protected GetQuotesByServiceIdPayload(Parcel in) {
//        this.chargeOriginalAmount = in.readString();
//        this.chargeAcceptNewestDifferenceAmount = in.readString();
//        this.quotes = in.createTypedArrayList(Quote.CREATOR);
//        this.topQuote = ((TopQuote) in.readValue((TopQuote.class.getClassLoader())));
//        this.remark = in.readString();
//        this.ct = in.readInt();
//    }


    public static final Creator<GetQuotesByServiceIdPayload> CREATOR = new Creator<GetQuotesByServiceIdPayload>() {
//        @Override
//        public GetQuotesByServiceIdPayload createFromParcel(Parcel source) {
//            return new GetQuotesByServiceIdPayload(source);
//        }
//
//        @Override
//        public GetQuotesByServiceIdPayload[] newArray(int size) {
//            return new GetQuotesByServiceIdPayload[size];
//        }
        @SuppressWarnings({
            "unchecked"})
        public GetQuotesByServiceIdPayload createFromParcel(Parcel in) {
            GetQuotesByServiceIdPayload instance = new GetQuotesByServiceIdPayload();
            instance.chargeOriginalAmount = ((String) in.readValue((String.class.getClassLoader())));
            instance.chargeAcceptNewestDifferenceAmount = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.topQuote, (java.lang.Object.class.getClassLoader()));
            in.readList(instance.quotes, (java.lang.Object.class.getClassLoader()));
            instance.remark = ((String) in.readValue((String.class.getClassLoader())));
            instance.ct = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public GetQuotesByServiceIdPayload[] newArray(int size) {
            return (new GetQuotesByServiceIdPayload[size]);
        }
    };
}