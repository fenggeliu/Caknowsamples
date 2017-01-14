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
    public final static Parcelable.Creator<HistoryResponse> CREATOR = new Creator<HistoryResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HistoryResponse createFromParcel(Parcel in) {
            HistoryResponse instance = new HistoryResponse();
            instance.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.payload = ((HistoryPayload) in.readValue((HistoryPayload.class.getClassLoader())));
            return instance;
        }

        public HistoryResponse[] newArray(int size) {
            return (new HistoryResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5862500599210071311L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HistoryPayload getPayload() {
        return payload;
    }

    public void setPayload(HistoryPayload payload) {
        this.payload = payload;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(message);
        dest.writeValue(payload);
    }

    public int describeContents() {
        return 0;
    }

}