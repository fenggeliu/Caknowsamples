package com.caknow.customer.util.net.reg;

import com.caknow.customer.util.net.content.LoginRequestPayload;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 1/6/17.
 */

public class RegistrationRequest {
    AccountType accountType;
    @SerializedName("token3st")
    String thirdPartyToken;
    String phone;
    String referralCode;
    //Only required if not using third party login
    String email;
    String password;
    String fName;
    String lName;

    /**
     *
     * @param accountType
     * @param thirdPartyToken
     * @param phone
     * @param referralCode
     */
    public RegistrationRequest(AccountType accountType,
                               String thirdPartyToken,
                               String phone,
                               String referralCode) {
        this.accountType = accountType;
        this.thirdPartyToken = thirdPartyToken;
        this.phone = phone;
        this.referralCode = referralCode;

    }

    /**
     *
     * @param email email adress
     * @param password password
     * @param fName first name
     * @param lName last name
     * @param phone phone number
     * @param referralCode referral code
     */
    public RegistrationRequest(String email,
                               String password,
                               String fName,
                               String lName,
                               String phone,
                               String referralCode){
        this.accountType = AccountType.CAKNOW_USER;
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.referralCode = referralCode;
    }


    // Default should be caknow user
    enum AccountType {
        FACEBOOK_USER, GOOGLE_USER, CAKNOW_USER;
    }

    public static String getJsonString(RegistrationRequest request){
        Gson gson = new Gson();

        return gson.toJson(request);
    }
}
