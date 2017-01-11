package com.caknow.customer.util.net;

import java.io.Serializable;

/**
 * Created by junu on 1/10/2017.
 */

public class CAKResponse<T> implements Serializable {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    private boolean success;
    private String message;
    private T payload;
}
