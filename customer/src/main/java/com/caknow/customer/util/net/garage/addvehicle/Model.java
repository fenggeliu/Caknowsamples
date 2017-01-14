package com.caknow.customer.util.net.garage.addvehicle;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable, Parcelable {

    @SerializedName("name")
    @Expose
    private  String name;
    @SerializedName("niceName")
    @Expose
    private String niceName;
    @SerializedName("years")
    @Expose
    private List<String> years;
    private final static long serialVersionUID = -7192033946044348695L;

    public String getName() {
        return name;
    }

    public String getNiceName() {
        return niceName;
    }

    public List<String> getYears() {
        return years;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.niceName);
        dest.writeStringList(this.years);
    }


    protected Model(Parcel in) {
        this.name = in.readString();
        this.niceName = in.readString();
        this.years = in.createStringArrayList();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}
