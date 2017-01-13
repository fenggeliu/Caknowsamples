package com.caknow.customer.garage;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MMY implements Serializable, Parcelable {

    public final static Parcelable.Creator<MMY> CREATOR = new Creator<MMY>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MMY createFromParcel(Parcel in) {
            MMY instance = new MMY();
            instance.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.payload = ((MMYPayload) in.readValue((MMYPayload.class.getClassLoader())));
            return instance;
        }

        public MMY[] newArray(int size) {
            return (new MMY[size]);
        }

    };
    private final static long serialVersionUID = -3986240792328667826L;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private MMYPayload payload;

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

    public MMYPayload getPayload() {
        return payload;
    }

    public void setPayload(MMYPayload payload) {
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
