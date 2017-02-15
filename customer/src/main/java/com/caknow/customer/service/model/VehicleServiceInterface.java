package com.caknow.customer.service.model;

import android.os.Parcelable;

/**
 * Created by junu on 1/11/2017.
 */

public interface VehicleServiceInterface extends Parcelable {

    String getDisplayTitle();

    String getIconUrl();

    Integer getQuoteCount();

    String getServiceField();

    String getServiceCatagory();

    String getOrderNo();

    Integer getStatus();

    String getServiceRequestId();

    Integer getType();

    Long getCreateTime();

    Long getDate();

    Double getCompletedRate();

    String getTimeframe();


}
