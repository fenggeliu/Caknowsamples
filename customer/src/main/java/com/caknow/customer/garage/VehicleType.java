package com.caknow.customer.garage;

/**
 * Created by junu on 1/2/2017.
 */

public class VehicleType {
    String _id;
    int type;
    String displayName;

    public VehicleType(String id, String displayName, int type){
        this._id = id == null ? "" : id;
        this.displayName = displayName == null ? "" : displayName;
        this.type = type;
    }

    public String getId(){
        return this._id;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public int getType(){
        return type;
    }
}
