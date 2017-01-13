package com.caknow.customer.util.net.history;

/**
 * Created by junu on 1/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Payload implements Serializable, Parcelable {

    public final static Parcelable.Creator<Payload> CREATOR = new Creator<Payload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Payload createFromParcel(Parcel in) {
            Payload instance = new Payload();
            in.readList(instance.list, (com.caknow.customer.util.net.history.List.class.getClassLoader()));
            return instance;
        }

        public Payload[] newArray(int size) {
            return (new Payload[size]);
        }

    };
    private final static long serialVersionUID = 2736016538613972890L;
    @SerializedName("list")
    @Expose
    private java.util.List<com.caknow.customer.util.net.history.List> list = null;

    public java.util.List<com.caknow.customer.util.net.history.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.caknow.customer.util.net.history.List> list) {
        this.list = list;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);
    }

    public int describeContents() {
        return 0;
    }

}