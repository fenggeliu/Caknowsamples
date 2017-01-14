package com.caknow.customer.util.net.payment;

/**
 * Created by junu on 12/31/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.service.ServicesPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddPaymentResponse implements Serializable, Parcelable {

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

    public AddPaymentResponse() {
    }

    protected AddPaymentResponse(Parcel in) {
        this.success = in.readByte() != 0;
        this.message = in.readString();
        this.servicesPayload = in.readParcelable(ServicesPayload.class.getClassLoader());
    }

    public static final Creator<AddPaymentResponse> CREATOR = new Creator<AddPaymentResponse>() {
        @Override
        public AddPaymentResponse createFromParcel(Parcel source) {
            return new AddPaymentResponse(source);
        }

        @Override
        public AddPaymentResponse[] newArray(int size) {
            return new AddPaymentResponse[size];
        }
    };
}