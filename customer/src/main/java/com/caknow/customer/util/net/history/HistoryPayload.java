package com.caknow.customer.util.net.history;

/**
 * Created by junu on 1/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HistoryPayload implements Serializable, Parcelable
{

    @SerializedName("list")
    @Expose
    private java.util.List<History> list = null;
    private final static long serialVersionUID = 2736016538613972890L;

    public java.util.List<History> getList() {
        return list;
    }

    public void setList(java.util.List<History> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
    }

    public HistoryPayload() {
    }

    protected HistoryPayload(Parcel in) {
        this.list = in.createTypedArrayList(History.CREATOR);
    }

    public static final Creator<HistoryPayload> CREATOR = new Creator<HistoryPayload>() {
        @Override
        public HistoryPayload createFromParcel(Parcel source) {
            return new HistoryPayload(source);
        }

        @Override
        public HistoryPayload[] newArray(int size) {
            return new HistoryPayload[size];
        }
    };
}