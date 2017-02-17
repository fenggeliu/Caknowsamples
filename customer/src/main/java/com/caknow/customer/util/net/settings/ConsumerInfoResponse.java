package com.caknow.customer.util.net.settings;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumerInfoResponse implements Serializable, Parcelable
{

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private ConsumerInfoPayload ConsumerInfoPayload;
    public final static Parcelable.Creator<ConsumerInfoResponse> CREATOR = new Creator<ConsumerInfoResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ConsumerInfoResponse createFromParcel(Parcel in) {
            ConsumerInfoResponse instance = new ConsumerInfoResponse();
            instance.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.ConsumerInfoPayload = ((ConsumerInfoPayload) in.readValue((ConsumerInfoPayload.class.getClassLoader())));
            return instance;
        }

        public ConsumerInfoResponse[] newArray(int size) {
            return (new ConsumerInfoResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8288191925936622486L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ConsumerInfoResponse() {
    }

    /**
     *
     * @param message
     * @param ConsumerInfoPayload
     * @param success
     */
    public ConsumerInfoResponse(boolean success, String message, ConsumerInfoPayload ConsumerInfoPayload) {
        super();
        this.success = success;
        this.message = message;
        this.ConsumerInfoPayload = ConsumerInfoPayload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ConsumerInfoResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ConsumerInfoResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public ConsumerInfoPayload getPayload() {
        return ConsumerInfoPayload;
    }

    public void setPayload(ConsumerInfoPayload ConsumerInfoPayload) {
        this.ConsumerInfoPayload = ConsumerInfoPayload;
    }

    public ConsumerInfoResponse withPayload(ConsumerInfoPayload ConsumerInfoPayload) {
        this.ConsumerInfoPayload = ConsumerInfoPayload;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(message);
        dest.writeValue(ConsumerInfoPayload);
    }

    public int describeContents() {
        return 0;
    }

}