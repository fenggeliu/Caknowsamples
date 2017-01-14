package com.caknow.customer.util.net.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by junu on 12/31/16.
 */

public class AuthenticationResponse implements Parcelable{
    private String _id;
    boolean success;
    private  String message;
    private  AuthenticationPayload payload;

    public String getUserId(){
        return TextUtils.isEmpty(this._id) ? "" : this._id;
    }

    public AuthenticationPayload getAuthenticationPayload(){
        return this.payload;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
        dest.writeParcelable(this.payload, flags);
    }

    public AuthenticationResponse() {
    }

    protected AuthenticationResponse(Parcel in) {
        this._id = in.readString();
        this.success = in.readByte() != 0;
        this.message = in.readString();
        this.payload = in.readParcelable(AuthenticationPayload.class.getClassLoader());
    }

    public static final Creator<AuthenticationResponse> CREATOR = new Creator<AuthenticationResponse>() {
        @Override
        public AuthenticationResponse createFromParcel(Parcel source) {
            return new AuthenticationResponse(source);
        }

        @Override
        public AuthenticationResponse[] newArray(int size) {
            return new AuthenticationResponse[size];
        }
    };
}
