package com.caknow.customer.util.net.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by junu on 1/11/2017.
 */
public class ServiceItem implements Serializable, Parcelable
{

    @SerializedName("catagoryId")
    @Expose
    private String catagoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("type")
    @Expose
    private long type;
    private final static long serialVersionUID = -5663899923799530039L;

    public String getCatagoryId() {
        return catagoryId;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public long getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.catagoryId);
        dest.writeString(this.name);
        dest.writeString(this.icon);
        dest.writeLong(this.type);
    }

    public ServiceItem() {
    }

    protected ServiceItem(Parcel in) {
        this.catagoryId = in.readString();
        this.name = in.readString();
        this.icon = in.readString();
        this.type = in.readLong();
    }

    public static final Creator<ServiceItem> CREATOR = new Creator<ServiceItem>() {
        @Override
        public ServiceItem createFromParcel(Parcel source) {
            return new ServiceItem(source);
        }

        @Override
        public ServiceItem[] newArray(int size) {
            return new ServiceItem[size];
        }
    };
}