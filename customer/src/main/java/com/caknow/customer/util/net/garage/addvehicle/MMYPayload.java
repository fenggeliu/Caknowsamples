package com.caknow.customer.util.net.garage.addvehicle;

/**
 * Created by junu on 1/10/2017.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MMYPayload implements Serializable, Parcelable {

    @SerializedName("makes")
    @Expose
    private List<Make> makes = null;
    public final static Parcelable.Creator<MMYPayload> CREATOR = new Creator<MMYPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MMYPayload createFromParcel(Parcel in) {
            MMYPayload instance = new MMYPayload();
            in.readList(instance.makes, (Make.class.getClassLoader()));
            return instance;
        }

        public MMYPayload[] newArray(int size) {
            return (new MMYPayload[size]);
        }

    };
    private final static long serialVersionUID = 6981888766891022527L;

    public List<Make> getMakes() {
        return makes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(makes);
    }

    public int describeContents() {
        return 0;
    }

}
