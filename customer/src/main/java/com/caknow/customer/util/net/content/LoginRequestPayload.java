package com.caknow.customer.util.net.content;

import com.caknow.customer.util.net.BaseRequestBody;
import com.google.gson.Gson;

/**
 * Created by junu on 1/6/17.
 */

public class LoginRequestPayload extends BaseRequestBody{
    String password;
    String email;


    public LoginRequestPayload(final String user, final String pass){
        this.email = user;
        this.password = pass;
    }


}
