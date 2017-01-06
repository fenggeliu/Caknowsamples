package com.caknow.customer.home;

import android.text.TextUtils;

/**
 * Created by junu on 1/1/17.
 */

public class HomeCardItem {
    String status;
    String vehicleId;
    String vehicleType;
    String date;
    String detailInfo;
    String action;

    public HomeCardItem(String status, String vehicleId, String vehicleType, String date, String detailInfo, String action){
        this.status = status;
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.date = date;
        this.detailInfo = detailInfo;
        this.action = action;
    }

    public String getStatus(){
        if(TextUtils.isEmpty(this.status)){
            this.status = "";
        }
        return this.status;
    }

    public String getVehicleId(){
        if(TextUtils.isEmpty(this.vehicleId)){
            this.vehicleId = "";
        }
        return this.vehicleId;
    }

    public String getVehicleType(){
        if(TextUtils.isEmpty(this.vehicleType)){
            this.vehicleType = "";
        }
        return this.vehicleType;
    }

    public String getDate(){
        if(TextUtils.isEmpty(this.date)){
            this.date = "";
        }
        return this.date;
    }

    public String getDetailInfo(){
        if(TextUtils.isEmpty(this.detailInfo)){
            this.detailInfo = "";
        }
        return this.detailInfo;
    }

    public String getAction(){
        if(TextUtils.isEmpty(this.action)){
            this.action = "";
        }
        return this.action;
    }
}
