package com.caknow.customer.util.net.auth;

/**
 * Created by junu on 1/1/17.
 */

public class AuthenticationRequest {
    String email;
    String password;

    public AuthenticationRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }
}
