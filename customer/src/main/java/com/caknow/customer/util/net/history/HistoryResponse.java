package com.caknow.customer.util.net.history;

/**
 * Created by junu on 1/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HistoryResponse implements Serializable, Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private HistoryPayload payload;
    private final static long serialVersionUID = 5862500599210071311L;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public HistoryPayload getPayload() {
        return payload;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.success);
        dest.writeString(this.message);
        dest.writeParcelable(this.payload, flags);
    }

    public HistoryResponse() {
    }

    protected HistoryResponse(Parcel in) {
        this.success = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.message = in.readString();
        this.payload = in.readParcelable(HistoryPayload.class.getClassLoader());
    }

    public static final Creator<HistoryResponse> CREATOR = new Creator<HistoryResponse>() {
        @Override
        public HistoryResponse createFromParcel(Parcel source) {
            return new HistoryResponse(source);
        }

        @Override
        public HistoryResponse[] newArray(int size) {
            return new HistoryResponse[size];
        }
    };
}