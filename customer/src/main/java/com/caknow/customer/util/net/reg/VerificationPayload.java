package com.caknow.customer.util.net.reg;

import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 12/31/16.
 */

public class VerificationPayload {
    boolean verified;

    public boolean isVerified(){
        return this.verified;
    }
}
