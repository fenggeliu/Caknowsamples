package com.caknow.customer.util.net.service;

/**
 * Created by junu on 1/11/2017.
 */



        import java.io.Serializable;
        import android.os.Parcel;
        import android.os.Parcelable;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Payload implements Serializable, Parcelable
{

    @SerializedName("list")
    @Expose
    private java.util.List<ServiceList> list = null;
    public final static Parcelable.Creator<Payload> CREATOR = new Creator<Payload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Payload createFromParcel(Parcel in) {
            Payload instance = new Payload();
            in.readList(instance.list, (ServiceList.class.getClassLoader()));
            return instance;
        }

        public Payload[] newArray(int size) {
            return (new Payload[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6633465839727654875L;

    public java.util.List<ServiceList> getList() {
        return list;
    }

    public void setList(java.util.List<ServiceList> list) {
        this.list = list;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);
    }

    public int describeContents() {
        return 0;
    }

}