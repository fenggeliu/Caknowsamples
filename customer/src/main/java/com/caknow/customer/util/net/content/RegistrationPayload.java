package com.caknow.customer.util.net.content;

import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 1/6/17.
 */

public class RegistrationPayload {

    AccountType accountType;
    @SerializedName("token3st") String thirdPartyToken;
    String phone;
    String referralCode;
    //Only required if not using third party login
    String email;
    String password;
    String fName;
    String lName;



    // Default should be caknow user
    enum AccountType{
         FACEBOOK_USER, GOOGLE_USER, CAKNOW_USER;
    }
}
