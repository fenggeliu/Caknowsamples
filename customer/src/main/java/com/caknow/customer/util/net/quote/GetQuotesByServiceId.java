package com.caknow.customer.util.net.quote;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetQuotesByServiceId implements Serializable, Parcelable {

    @SerializedName("payload")
    @Expose
    private GetQuotesByServiceIdPayload getQuotesByServiceIdPayload;
    private final static long serialVersionUID = 6694703299894624120L;

    public GetQuotesByServiceIdPayload getGetQuotesByServiceIdPayload() {
        return getQuotesByServiceIdPayload;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.getQuotesByServiceIdPayload, flags);
    }

    public GetQuotesByServiceId() {
    }

    protected GetQuotesByServiceId(Parcel in) {
        this.getQuotesByServiceIdPayload = in.readParcelable(GetQuotesByServiceIdPayload.class.getClassLoader());
    }

    public static final Creator<GetQuotesByServiceId> CREATOR = new Creator<GetQuotesByServiceId>() {
        @Override
        public GetQuotesByServiceId createFromParcel(Parcel source) {
            return new GetQuotesByServiceId(source);
        }

        @Override
        public GetQuotesByServiceId[] newArray(int size) {
            return new GetQuotesByServiceId[size];
        }
    };
}