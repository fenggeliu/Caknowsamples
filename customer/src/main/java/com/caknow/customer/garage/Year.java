package com.caknow.customer.garage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by junu on 1/10/2017.
 */

public class Year implements Parcelable {

    public static final Creator<Year> CREATOR = new Creator<Year>() {
        @Override
        public Year createFromParcel(Parcel source) {
            return new Year(source);
        }

        @Override
        public Year[] newArray(int size) {
            return new Year[size];
        }
    };
    String value;

    public Year(final String value) {
        this.value = value;
    }

    protected Year(Parcel in) {
        this.value = in.readString();
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
    }
}
