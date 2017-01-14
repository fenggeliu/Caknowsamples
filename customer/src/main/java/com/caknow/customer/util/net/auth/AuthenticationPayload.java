package com.caknow.customer.util.net.auth;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 12/31/16.
 */

public class AuthenticationPayload implements Parcelable {
    public String get_id() {
        return _id;
    }

    public String getToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getPubnubChnl() {
        return pubnubChnl;
    }

    public String getStripeCusToken() {
        return stripeCusToken;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getLanguage() {
        return language;
    }

    public boolean getVerificationStatus() {
        return verificationStatus;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public boolean getHasEMail() {
        return hasEmail;
    }


    String _id;
    @SerializedName("token") String accessToken;
    String refreshToken;
    String pubnubChnl;
    String stripeCusToken;
    boolean verified;
    int language;
    boolean verificationStatus;
    boolean hasEmail;
    String fName;
    String lName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.accessToken);
        dest.writeString(this.refreshToken);
        dest.writeString(this.pubnubChnl);
        dest.writeString(this.stripeCusToken);
        dest.writeByte(this.verified ? (byte) 1 : (byte) 0);
        dest.writeInt(this.language);
        dest.writeByte(this.verificationStatus ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasEmail ? (byte) 1 : (byte) 0);
        dest.writeString(this.fName);
        dest.writeString(this.lName);
    }

    public AuthenticationPayload() {
    }

    protected AuthenticationPayload(Parcel in) {
        this._id = in.readString();
        this.accessToken = in.readString();
        this.refreshToken = in.readString();
        this.pubnubChnl = in.readString();
        this.stripeCusToken = in.readString();
        this.verified = in.readByte() != 0;
        this.language = in.readInt();
        this.verificationStatus = in.readByte() != 0;
        this.hasEmail = in.readByte() != 0;
        this.fName = in.readString();
        this.lName = in.readString();
    }

    public static final Parcelable.Creator<AuthenticationPayload> CREATOR = new Parcelable.Creator<AuthenticationPayload>() {
        @Override
        public AuthenticationPayload createFromParcel(Parcel source) {
            return new AuthenticationPayload(source);
        }

        @Override
        public AuthenticationPayload[] newArray(int size) {
            return new AuthenticationPayload[size];
        }
    };
}
