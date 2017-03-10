package com.caknow.customer.util.net.transaction;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.caknow.customer.util.net.service.quotes.PriceDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionCodesPayload implements Serializable, Parcelable
{

    @SerializedName("details")
    @Expose
    private List<PriceDetail> details = null;
    @SerializedName("acceptedPromoCodes")
    @Expose
    private List<String> acceptedPromoCodes = null;
    @SerializedName("rejectedPromoCodes")
    @Expose
    private List<String> rejectedPromoCodes = null;
    @SerializedName("refundAmount")
    @Expose
    private  long refundAmount;
    public final static Parcelable.Creator<PromotionCodesPayload> CREATOR = new Creator<PromotionCodesPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PromotionCodesPayload createFromParcel(Parcel in) {
            PromotionCodesPayload instance = new PromotionCodesPayload();
            in.readList(instance.details, (com.caknow.customer.util.net.service.quotes.PriceDetail.class.getClassLoader()));
            in.readList(instance.acceptedPromoCodes, (java.lang.String.class.getClassLoader()));
            in.readList(instance.rejectedPromoCodes, (java.lang.String.class.getClassLoader()));
            instance.refundAmount = ((Long) in.readValue((Long.class.getClassLoader())));
            return instance;
        }

        public PromotionCodesPayload[] newArray(int size) {
            return (new PromotionCodesPayload[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7175135727230800890L;

    /**
     * No args constructor for use in serialization
     *
     */
    public PromotionCodesPayload() {
    }

    /**
     *
     * @param acceptedPromoCodes
     * @param details
     * @param rejectedPromoCodes
     */
    public PromotionCodesPayload(List<PriceDetail> details, List<String> acceptedPromoCodes, List<String> rejectedPromoCodes, Long refundAmont) {
        super();
        this.details = details;
        this.acceptedPromoCodes = acceptedPromoCodes;
        this.rejectedPromoCodes = rejectedPromoCodes;
        this.refundAmount = refundAmont;
    }

    public List<PriceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PriceDetail> details) {
        this.details = details;
    }

    public List<String> getAcceptedPromoCodes() {
        return acceptedPromoCodes;
    }

    public void setAcceptedPromoCodes(List<String> acceptedPromoCodes) {
        this.acceptedPromoCodes = acceptedPromoCodes;
    }

    public List<String> getRejectedPromoCodes() {
        return rejectedPromoCodes;
    }

    public void setRejectedPromoCodes(List<String> rejectedPromoCodes) {
        this.rejectedPromoCodes = rejectedPromoCodes;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(details);
        dest.writeList(acceptedPromoCodes);
        dest.writeList(rejectedPromoCodes);
        dest.writeValue(refundAmount);
    }

    public int describeContents() {
        return 0;
    }

}