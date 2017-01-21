package com.caknow.customer.util.net.service;

/**
 * Created by junu on 1/11/2017.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServicesPayload implements Serializable, Parcelable
{

    @SerializedName("list")
    @Expose
    private java.util.List<ServiceItem> list = null;
    private final static long serialVersionUID = 6633465839727654875L;

    public java.util.List<ServiceItem> getList() {
        return list;
    }

    public ServicesPayload() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
    }

    protected ServicesPayload(Parcel in) {
        this.list = in.createTypedArrayList(ServiceItem.CREATOR);
    }

    public static final Creator<ServicesPayload> CREATOR = new Creator<ServicesPayload>() {
        @Override
        public ServicesPayload createFromParcel(Parcel source) {
            return new ServicesPayload(source);
        }

        @Override
        public ServicesPayload[] newArray(int size) {
            return new ServicesPayload[size];
        }
    };
}