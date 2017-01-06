package com.caknow.customer.util.net;

import android.text.TextUtils;

/**
 * Created by junu on 12/31/16.
 */

public class AuthenticationResponse {
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
}
