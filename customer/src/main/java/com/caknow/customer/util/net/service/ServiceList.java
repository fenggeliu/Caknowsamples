package com.caknow.customer.util.net.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by junu on 1/11/2017.
 */
public class ServiceList implements Serializable, Parcelable
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
    public final static Parcelable.Creator<ServiceList> CREATOR = new Creator<ServiceList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceList createFromParcel(Parcel in) {
            ServiceList instance = new ServiceList();
            instance.catagoryId = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            instance.type = ((long) in.readValue((long.class.getClassLoader())));
            return instance;
        }

        public ServiceList[] newArray(int size) {
            return (new ServiceList[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5663899923799530039L;

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(catagoryId);
        dest.writeValue(name);
        dest.writeValue(icon);
        dest.writeValue(type);
    }

    public int describeContents() {
        return 0;
    }

}