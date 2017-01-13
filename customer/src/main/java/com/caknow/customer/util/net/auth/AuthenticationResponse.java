package com.caknow.customer.util.net.auth;

import android.text.TextUtils;

/**
 * Created by junu on 12/31/16.
 */

public class AuthenticationResponse {
    boolean success;
    private String _id;
    private String message;
    private AuthenticationPayload payload;

    public String getUserId() {
        return TextUtils.isEmpty(this._id) ? "" : this._id;
    }

    public AuthenticationPayload getAuthenticationPayload() {
        return this.payload;
    }
}
