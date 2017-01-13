package com.caknow.customer.garage;


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
    public final static Parcelable.Creator<Model> CREATOR = new Creator<Model>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Model createFromParcel(Parcel in) {
            Model instance = new Model();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.niceName = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.years, (java.lang.String.class.getClassLoader()));
            return instance;
        }

        public Model[] newArray(int size) {
            return (new Model[size]);
        }

    };
    private final static long serialVersionUID = -7192033946044348695L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(niceName);
        dest.writeList(years);
    }

    public int describeContents() {
        return 0;
    }

}
