package com.caknow.customer.service.model;

/**
 * Created by jkang on 1/4/17.
 */

/**
 * A dummy item representing a piece of content.
 */
public class ServiceItem{
    public final String id;
    public final String content;
    public final String details;

    public ServiceItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}