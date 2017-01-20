package com.caknow.customer.util.net.garage;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GarageResponse implements Serializable, Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private GaragePayload payload;
    public final static Parcelable.Creator<GarageResponse> CREATOR = new Creator<GarageResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GarageResponse createFromParcel(Parcel in) {
            GarageResponse instance = new GarageResponse();
            instance.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.payload = ((GaragePayload) in.readValue((GaragePayload.class.getClassLoader())));
            return instance;
        }

        public GarageResponse[] newArray(int size) {
            return (new GarageResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8313058017276733907L;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public GaragePayload getPayload() {
        return payload;
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