package com.caknow.customer.util.net.reg;

import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.BaseRequestBody;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 1/6/17.
 */

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class RegistrationRequest extends BaseRequestBody{
    private String accountType;
    @SerializedName("token3st")
    private String thirdPartyToken;
    private String phone;
    private String referralCode;
    //Only required if not using third party login
    private String email;
    private String password;
    private String fName;
    private String lName;

    /**
     *
     * @param accountType String value of account type (FACEBOOK_USER, GOOGLE_USER)
     * @param thirdPartyToken String value of third party token
     * @param phone String value of phone
     * @param referralCode String value of Referral code
     */
    public RegistrationRequest(String accountType,
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
     * @param fName first niceName
     * @param lName last niceName
     * @param phone phone number
     * @param referralCode referral code
     */
    public RegistrationRequest(String email,
                               String password,
                               String fName,
                               String lName,
                               String phone,
                               String referralCode){
        this.accountType = Constants.ACCOUNT_TYPE_CAKNOW;
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.referralCode = referralCode;
    }

    class AccountType{

    }


}
