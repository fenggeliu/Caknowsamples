package com.caknow.customer.garage;

import java.util.List;

/**
 * Created by junu on 1/7/2017.
 */

public class Year extends MMY {


    List<String> years;
    public Year(){
        this.type = 2;
    }
    public List<String> getYears(){
        return this.years;
    }
}
