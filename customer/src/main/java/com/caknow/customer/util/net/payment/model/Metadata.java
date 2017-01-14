package com.caknow.customer.util.net.payment.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata implements Serializable, Parcelable
{

    @SerializedName("for")
    @Expose
    private String _for;
    @SerializedName("_id")
    @Expose
    private String id;
    public final static Parcelable.Creator<Metadata> CREATOR = new Creator<Metadata>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Metadata createFromParcel(Parcel in) {
            Metadata instance = new Metadata();
            instance._for = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Metadata[] newArray(int size) {
            return (new Metadata[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7092992343610286549L;

    public String getFor() {
        return _for;
    }

    public void setFor(String _for) {
        this._for = _for;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_for);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}