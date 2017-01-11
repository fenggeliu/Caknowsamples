package com.caknow.customer.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by junu on 1/10/2017.
 */

public class Repair {


    @SerializedName("serviceRequestId")
    @Expose
    private String serviceRequestId;
    @SerializedName("createTime")
    @Expose
    private Integer createTime;
    @SerializedName("orderNo")
    @Expose
    private String orderNo;
    @SerializedName("serviceCatagory")
    @Expose
    private String serviceCatagory;
    @SerializedName("serviceField")
    @Expose
    private String serviceField;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("quoteCount")
    @Expose
    private Integer quoteCount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("type")
    @Expose
    private Integer type;

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getServiceCatagory() {
        return serviceCatagory;
    }

    public void setServiceCatagory(String serviceCatagory) {
        this.serviceCatagory = serviceCatagory;
    }

    public String getServiceField() {
        return serviceField;
    }

    public void setServiceField(String serviceField) {
        this.serviceField = serviceField;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(Integer quoteCount) {
        this.quoteCount = quoteCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
