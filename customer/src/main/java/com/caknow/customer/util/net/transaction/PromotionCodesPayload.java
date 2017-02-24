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
    private List<Object> acceptedPromoCodes = null;
    @SerializedName("rejectedPromoCodes")
    @Expose
    private List<String> rejectedPromoCodes = null;
    public final static Parcelable.Creator<PromotionCodesPayload> CREATOR = new Creator<PromotionCodesPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PromotionCodesPayload createFromParcel(Parcel in) {
            PromotionCodesPayload instance = new PromotionCodesPayload();
            in.readList(instance.details, (com.caknow.customer.util.net.service.quotes.PriceDetail.class.getClassLoader()));
            in.readList(instance.acceptedPromoCodes, (java.lang.Object.class.getClassLoader()));
            in.readList(instance.rejectedPromoCodes, (java.lang.String.class.getClassLoader()));
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
    public PromotionCodesPayload(List<PriceDetail> details, List<Object> acceptedPromoCodes, List<String> rejectedPromoCodes) {
        super();
        this.details = details;
        this.acceptedPromoCodes = acceptedPromoCodes;
        this.rejectedPromoCodes = rejectedPromoCodes;
    }

    public List<PriceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PriceDetail> details) {
        this.details = details;
    }

    public List<Object> getAcceptedPromoCodes() {
        return acceptedPromoCodes;
    }

    public void setAcceptedPromoCodes(List<Object> acceptedPromoCodes) {
        this.acceptedPromoCodes = acceptedPromoCodes;
    }

    public List<String> getRejectedPromoCodes() {
        return rejectedPromoCodes;
    }

    public void setRejectedPromoCodes(List<String> rejectedPromoCodes) {
        this.rejectedPromoCodes = rejectedPromoCodes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(details);
        dest.writeList(acceptedPromoCodes);
        dest.writeList(rejectedPromoCodes);
    }

    public int describeContents() {
        return 0;
    }

}