package com.caknow.customer.util.net.payment;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.caknow.customer.util.net.payment.model.PaymentsPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaymentsResponse implements Serializable, Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private PaymentsPayload payload;
    public final static Parcelable.Creator<GetPaymentsResponse> CREATOR = new Creator<GetPaymentsResponse>() {

        @SuppressWarnings({
                "unchecked"
        })
        public GetPaymentsResponse createFromParcel(Parcel in) {
            GetPaymentsResponse instance = new GetPaymentsResponse();
            instance.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.payload = ((PaymentsPayload) in.readValue((PaymentsPayload.class.getClassLoader())));
            return instance;
        }

        public GetPaymentsResponse[] newArray(int size) {
            return (new GetPaymentsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5905469642279883420L;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public PaymentsPayload getPayload() {
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