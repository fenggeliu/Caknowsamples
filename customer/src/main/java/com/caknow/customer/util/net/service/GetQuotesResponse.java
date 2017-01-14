
package com.caknow.customer.util.net.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.service.quotes.QuotePayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetQuotesResponse implements Parcelable {

    @SerializedName("payload")
    @Expose
    private QuotePayload payload;
    private final static long serialVersionUID = -5462666189734470538L;

    public QuotePayload getPayload() {
        return payload;
    }

    public void setPayload(QuotePayload payload) {
        this.payload = payload;
    }

    public GetQuotesResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.payload, flags);
    }

    protected GetQuotesResponse(Parcel in) {
        this.payload = in.readParcelable(QuotePayload.class.getClassLoader());
    }

    public static final Creator<GetQuotesResponse> CREATOR = new Creator<GetQuotesResponse>() {
        @Override
        public GetQuotesResponse createFromParcel(Parcel source) {
            return new GetQuotesResponse(source);
        }

        @Override
        public GetQuotesResponse[] newArray(int size) {
            return new GetQuotesResponse[size];
        }
    };
}