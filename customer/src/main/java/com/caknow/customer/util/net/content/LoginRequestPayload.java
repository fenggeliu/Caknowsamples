package com.caknow.customer.util.net.content;

import com.google.gson.Gson;

/**
 * Created by junu on 1/6/17.
 */

public class LoginRequestPayload {
    String password;
    String email;


    public LoginRequestPayload(final String user, final String pass){
        this.email = user;
        this.password = pass;
    }

    public static String getJsonString(LoginRequestPayload payload){
        Gson gson = new Gson();

        return gson.toJson(payload);
    }
}
