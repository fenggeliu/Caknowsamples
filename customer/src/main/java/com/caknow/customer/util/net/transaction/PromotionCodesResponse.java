package com.caknow.customer.util.net.transaction;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.caknow.customer.util.net.transaction.PromotionCodesPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionCodesResponse implements Serializable, Parcelable
{

    @SerializedName("payload")
    @Expose
    private PromotionCodesPayload payload;
    public final static Parcelable.Creator<PromotionCodesResponse> CREATOR = new Creator<PromotionCodesResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PromotionCodesResponse createFromParcel(Parcel in) {
            PromotionCodesResponse instance = new PromotionCodesResponse();
            instance.payload = ((PromotionCodesPayload) in.readValue((PromotionCodesPayload.class.getClassLoader())));
            return instance;
        }

        public PromotionCodesResponse[] newArray(int size) {
            return (new PromotionCodesResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6856445007655668812L;

    /**
     * No args constructor for use in serialization
     *
     */
    public PromotionCodesResponse() {
    }

    /**
     *
     * @param payload
     */
    public PromotionCodesResponse(PromotionCodesPayload payload) {
        super();
        this.payload = payload;
    }

    public PromotionCodesPayload getPayload() {
        return payload;
    }

    public void setPayload(PromotionCodesPayload payload) {
        this.payload = payload;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(payload);
    }

    public int describeContents() {
        return 0;
    }

}