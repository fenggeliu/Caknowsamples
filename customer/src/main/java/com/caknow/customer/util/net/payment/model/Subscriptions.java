package com.caknow.customer.util.net.payment.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscriptions implements Serializable, Parcelable
{

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;
    @SerializedName("total_count")
    @Expose
    private Long totalCount;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<Subscriptions> CREATOR = new Creator<Subscriptions>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Subscriptions createFromParcel(Parcel in) {
            Subscriptions instance = new Subscriptions();
            instance.object = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.data, (java.lang.Object.class.getClassLoader()));
            instance.hasMore = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.totalCount = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Subscriptions[] newArray(int size) {
            return (new Subscriptions[size]);
        }

    }
            ;
    private final static long serialVersionUID = -9152093596594177119L;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(object);
        dest.writeList(data);
        dest.writeValue(hasMore);
        dest.writeValue(totalCount);
        dest.writeValue(url);
    }

    public int describeContents() {
        return 0;
    }

}

