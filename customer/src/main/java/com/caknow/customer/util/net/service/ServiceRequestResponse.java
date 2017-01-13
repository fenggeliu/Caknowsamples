package com.caknow.customer.util.net.service;

/**
 * Created by junu on 12/31/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceRequestResponse implements Serializable, Parcelable {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private Payload payload;
    private final static long serialVersionUID = -1027863414252002434L;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
        dest.writeParcelable(this.payload, flags);
    }

    public ServiceRequestResponse() {
    }

    protected ServiceRequestResponse(Parcel in) {
        this.success = in.readByte() != 0;
        this.message = in.readString();
        this.payload = in.readParcelable(Payload.class.getClassLoader());
    }

    public static final Creator<ServiceRequestResponse> CREATOR = new Creator<ServiceRequestResponse>() {
        @Override
        public ServiceRequestResponse createFromParcel(Parcel source) {
            return new ServiceRequestResponse(source);
        }

        @Override
        public ServiceRequestResponse[] newArray(int size) {
            return new ServiceRequestResponse[size];
        }
    };
}