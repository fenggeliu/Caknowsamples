package com.caknow.customer.util.net.history;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceList implements Serializable, Parcelable {

    public final static Parcelable.Creator<ServiceList> CREATOR = new Creator<ServiceList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceList createFromParcel(Parcel in) {
            ServiceList instance = new ServiceList();
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            instance.catagory = ((String) in.readValue((String.class.getClassLoader())));
            instance.field = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ServiceList[] newArray(int size) {
            return (new ServiceList[size]);
        }

    };
    private final static long serialVersionUID = -4997352924890943698L;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("catagory")
    @Expose
    private String catagory;
    @SerializedName("field")
    @Expose
    private String field;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(icon);
        dest.writeValue(catagory);
        dest.writeValue(field);
    }

    public int describeContents() {
        return 0;
    }

}