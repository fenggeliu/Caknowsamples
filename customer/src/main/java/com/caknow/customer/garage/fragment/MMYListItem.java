package com.caknow.customer.garage.fragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by junu on 1/10/2017.
 */

public class MMYListItem implements Parcelable {

    public final int type;
    public final int position;
    public final String displayName;
    public final String niceName;


    public MMYListItem(int type, int position, String displayName, String name){
        this.type = type;
        this.position = position;
        this.displayName = displayName;
        this.niceName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeInt(this.position);
        dest.writeString(this.displayName);
        dest.writeString(this.niceName);
    }

    protected MMYListItem(Parcel in) {
        this.type = in.readInt();
        this.position = in.readInt();
        this.displayName = in.readString();
        this.niceName = in.readString();
    }

    public static final Creator<MMYListItem> CREATOR = new Creator<MMYListItem>() {
        @Override
        public MMYListItem createFromParcel(Parcel source) {
            return new MMYListItem(source);
        }

        @Override
        public MMYListItem[] newArray(int size) {
            return new MMYListItem[size];
        }
    };
}
