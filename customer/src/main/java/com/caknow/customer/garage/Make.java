package com.caknow.customer.garage;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Make implements Serializable, Parcelable {

    public final static Parcelable.Creator<Make> CREATOR = new Creator<Make>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Make createFromParcel(Parcel in) {
            Make instance = new Make();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.niceName = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.models, (com.caknow.customer.garage.Model.class.getClassLoader()));
            return instance;
        }

        public Make[] newArray(int size) {
            return (new Make[size]);
        }

    };
    private final static long serialVersionUID = 5593767957091260668L;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("niceName")
    @Expose
    private String niceName;
    @SerializedName("models")
    @Expose
    private List<Model> models;

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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(niceName);
        dest.writeList(models);
    }

    public int describeContents() {
        return 0;
    }

}
