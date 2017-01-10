package com.caknow.customer.garage;

import java.util.List;

/**
 * Created by junu on 1/7/2017.
 */

public class Model extends MMY {
    List<Year> years;

    public Model(){
        this.type = 1;
    }

    public String getNiceName(){
        return this.niceName;
    }

    public String getName(){
        return this.name;
    }

    public List<Year> getYears(){
        return this.years;
    }
}
