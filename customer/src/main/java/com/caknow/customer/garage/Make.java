package com.caknow.customer.garage;

import java.util.List;

/**
 * Created by junu on 1/7/2017.
 */

public class Make extends MMY {
    List<Model> models;

    public Make(){
        this.type = 0;
    }

    public String getNiceName(){
        return this.niceName;
    }

    public String getName(){
        return this.name;
    }

    public List<Model> getModels(){
        return this.models;
    }
}
