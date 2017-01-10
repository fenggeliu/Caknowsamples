package com.caknow.customer.garage;

import java.util.List;

/**
 * Created by junu on 1/7/2017.
 */

public abstract class MMY {
    String name;
    String niceName;
    int type;

    public String getNiceName(){
        return this.niceName;
    }

    public String getName(){
        return this.name;
    }

}
