package com.caknow.customer.util.net.garage.addvehicle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Make implements Serializable, Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("niceName")
    @Expose
    private String niceName;
    @SerializedName("models")
    @Expose
    private List<Model> models;
    private final static long serialVersionUID = 5593767957091260668L;

    public String getName() {
        return name;
    }


    public String getNiceName() {
        return niceName;
    }


    public List<Model> getModels() {
        return models;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.niceName);
        dest.writeTypedList(this.models);
    }

    public Make() {
    }

    protected Make(Parcel in) {
        this.name = in.readString();
        this.niceName = in.readString();
        this.models = in.createTypedArrayList(Model.CREATOR);
    }

    public static final Creator<Make> CREATOR = new Creator<Make>() {
        @Override
        public Make createFromParcel(Parcel source) {
            return new Make(source);
        }

        @Override
        public Make[] newArray(int size) {
            return new Make[size];
        }
    };
}
