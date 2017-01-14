package com.caknow.customer.util.net.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by junu on 12/31/16.
 */

public class ServiceTypeResponse implements Serializable, Parcelable {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private ServicesPayload servicesPayload;
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

    public ServicesPayload getServicesPayload() {
        return servicesPayload;
    }

    public void setServicesPayload(ServicesPayload servicesPayload) {
        this.servicesPayload = servicesPayload;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
        dest.writeParcelable(this.servicesPayload, flags);
    }

    public ServiceTypeResponse() {
    }

    protected ServiceTypeResponse(Parcel in) {
        this.success = in.readByte() != 0;
        this.message = in.readString();
        this.servicesPayload = in.readParcelable(ServicesPayload.class.getClassLoader());
    }

    public static final Creator<ServiceTypeResponse> CREATOR = new Creator<ServiceTypeResponse>() {
        @Override
        public ServiceTypeResponse createFromParcel(Parcel source) {
            return new ServiceTypeResponse(source);
        }

        @Override
        public ServiceTypeResponse[] newArray(int size) {
            return new ServiceTypeResponse[size];
        }
    };
}