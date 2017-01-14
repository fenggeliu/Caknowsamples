
package com.caknow.customer.util.net.garage.addvehicle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MMYResponse implements Serializable, Parcelable
{

    @SerializedName("success")
    @Expose
    private  Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private MMYPayload payload;
    public final static Parcelable.Creator<MMYResponse> CREATOR = new Creator<MMYResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MMYResponse createFromParcel(Parcel in) {
            MMYResponse instance = new MMYResponse();
            instance.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.payload = ((MMYPayload) in.readValue((MMYPayload.class.getClassLoader())));
            return instance;
        }

        public MMYResponse[] newArray(int size) {
            return (new MMYResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3986240792328667826L;

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
        return  0;
    }

}
