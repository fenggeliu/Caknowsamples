package com.caknow.customer.util.net.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 12/31/16.
 */

public class AuthenticationPayload {
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
}