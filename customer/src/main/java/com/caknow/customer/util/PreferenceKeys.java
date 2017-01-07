package com.caknow.customer.util;

/**
 * Created by junu on 1/6/17.
 */

public class PreferenceKeys {
    public static final String PREFERENCES = PreferenceKeys.class.getName();

    public static final String REFRESH_TOKEN = PREFERENCES + ".refreshToken";
    public static final String ACCESS_TOKEN = PREFERENCES + ".accessToken";
    public static final String USER_ID = PREFERENCES + ".user_id";
    public static final String USER_FNAME = PREFERENCES + ".user_firstName";
    public static final String USER_LNAME = PREFERENCES + ".user_lname";
    public static final String USER_VERIFICATION_STATUS = PREFERENCES + ".user_verification_status";
    public static final String PUBNUB_CHANNEL = PREFERENCES + ".pubnub_channel";
    public static final String STRIPE_TOKEN = PREFERENCES + ".user_stripe_token";


}
