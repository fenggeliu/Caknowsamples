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
    public final static Parcelable.Creator<HistoryPayload> CREATOR = new Creator<HistoryPayload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HistoryPayload createFromParcel(Parcel in) {
            HistoryPayload instance = new HistoryPayload();
            in.readList(instance.list, (History.class.getClassLoader()));
            return instance;
        }

        public HistoryPayload[] newArray(int size) {
            return (new HistoryPayload[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2736016538613972890L;

    public java.util.List<History> getList() {
        return list;
    }

    public void setList(java.util.List<History> list) {
        this.list = list;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);
    }

    public int describeContents() {
        return 0;
    }

}